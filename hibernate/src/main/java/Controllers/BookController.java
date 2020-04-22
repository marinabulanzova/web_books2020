package Controllers;
import DAO.*;
import form.BookSearch;
import models.Author;
import models.Basket_customer;
import models.Book;
import models.Book_author;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;

@Controller
public class BookController {
    @Autowired
    SessionFactory factory;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String findAll(HttpServletRequest request, ModelMap model) {
        Session session = factory.openSession();
        BookDAO books = new BookDAO(session);
        if (request.getUserPrincipal() != null) {
            /*models.User user = (models.User)request.getUserPrincipal();
            model.addAttribute("id", user.getId_user());
            model.addAttribute("admin", user.getAdmin());*/
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            models.User user = (models.User)auth.getPrincipal();
            model.addAttribute("id", user.getId_user());
            model.addAttribute("admin", user.getAdmin());
        }
        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        models.User user = (models.User)auth.getPrincipal();
        model.addAttribute("auth", user.getId_user());*/
        model.addAttribute("BooksList", books.findAll());
        return "books";
    }

    @RequestMapping(value = "/search_books", method = RequestMethod.GET)
    public String find(@ModelAttribute BookSearch book_search, ModelMap model) {
        Session session = factory.openSession();
        BookDAO books = new BookDAO(session);
        model.addAttribute("BooksList",
                books.find(book_search.getTitle(), book_search.getGenre(), book_search.getPublishing_house(),
                        book_search.getMin_p_year(),book_search.getMax_p_year(), book_search.getMin_p_count(),
                        book_search.getMax_p_count(), book_search.getCount(), book_search.getCover(), book_search.getMax_price(),
                        book_search.getMin_price(), book_search.getName_author()));
        model.addAttribute("book", book_search);
        return "books/search_results";
    }

    @RequestMapping(value = "/add_books", method = RequestMethod.GET)
    public String book_add(ModelMap model) {
        return "books/add";
    }

    @RequestMapping(value = "/edit_books", method = RequestMethod.POST)
    public String edit_book(@RequestParam Integer id,
                              ModelMap model) {
        Session session = factory.openSession();
        BookDAO books = new BookDAO(session);
        Book book = books.getById(id);
        model.addAttribute("id", id);
        model.addAttribute("title", book.getTitle());
        model.addAttribute("genre", book.getGenre());
        model.addAttribute("publishing_house", book.getPublishing_house());
        model.addAttribute("publication_year", book.getPublication_year());
        model.addAttribute("page_count", book.getPage_count());
        model.addAttribute("count_book", book.getCount_book());
        model.addAttribute("cover", book.getCover());
        model.addAttribute("price", book.getPrice());

        return "books/edit";
    }

    @RequestMapping(value = "/edit_done", method = RequestMethod.POST)
    public String edit_done(Integer id,
                            @RequestParam String title, @RequestParam String genre,
                            @RequestParam String publishing_house, @RequestParam Integer publication_year,
                            @RequestParam Integer page_count, @RequestParam Integer count_book,
                            @RequestParam String cover, @RequestParam Double price, @RequestParam List<String> authors, ModelMap model) {
        Session session = factory.openSession();
        BookDAO books = new BookDAO(session);
        if (title.equals("")) {
            model.addAttribute("error", true);
            model.addAttribute("title", title);
            model.addAttribute("genre", genre);
            model.addAttribute("publishing_house", publishing_house);
            model.addAttribute("publication_year", publication_year);
            model.addAttribute("page_count", page_count);
            model.addAttribute("cover", cover);
            model.addAttribute("price", price);
            model.addAttribute("authors", authors);

            if (id != null) {
                model.addAttribute("id", id);
                return "books/edit";
            } else {
                return "books/add";
            }
        }
        Book book;
        if (id != null) {
            book = books.getById(id);
            book.setGenre(genre);
            book.setTitle(title);
            book.setPublishing_house(publishing_house);
            book.setPublication_year(publication_year);
            book.setPage_count(page_count);
            book.setCount_book(count_book);
            book.setCover(cover);
            book.setPrice(price);
        } else {
            book = new Book(genre, title, publishing_house, publication_year,
                    page_count, count_book, cover, price);
            session.getTransaction().begin();
            books.save(book);
            session.getTransaction().commit();
        }
        List<Book_author> book_authors = new ArrayList();
        List<Author> author;
        Author a;
        AuthorDAO authors_dao = new AuthorDAO(session);
        for (String name : authors) {
            author = authors_dao.find(name);
            if (author.size() == 0) {
                a = new Author(name);
                session.getTransaction().begin();
                authors_dao.save(a);
                session.getTransaction().commit();
            } else {
                a = author.get(0);
            }
            Book_author b_a = new Book_author(book, a);
            book_authors.add(b_a);
        }
        book.setBook_authors(book_authors);

        session.getTransaction().begin();
        if(id != null) {
            books.update(book);
        } else {
            books.save(book);
        }
        session.getTransaction().commit();

        model.addAttribute("BooksList", books.findAll());
        return "books";
    }

    @RequestMapping(value = "/rm_book", method = RequestMethod.POST)
    public String remove_book(@RequestParam Integer id,
                                ModelMap model) {
        Session session = factory.openSession();
        BookDAO books = new BookDAO(session);

        session.getTransaction().begin();
        books.delete(books.getById(id));
        session.getTransaction().commit();

        model.addAttribute("BooksList", books.findAll());
        return "books";
    }

    @RequestMapping(value = "/detailed_books", method = RequestMethod.GET)
    public String detailed_book(@RequestParam Integer id,
                                ModelMap model) {
        Session session = factory.openSession();
        BookDAO books = new BookDAO(session);
        Book book = books.getById(id);
        model.addAttribute("id", id);
        model.addAttribute("title", book.getTitle());
        model.addAttribute("genre", book.getGenre());
        model.addAttribute("publishing_house", book.getPublishing_house());
        model.addAttribute("publication_year", book.getPublication_year());
        model.addAttribute("page_count", book.getPage_count());
        model.addAttribute("count_book", book.getCount_book());
        model.addAttribute("cover", book.getCover());
        model.addAttribute("price", book.getPrice());
        model.addAttribute("book_authors", book.getBook_authors());
        return "books/detailed";
    }

    @RequestMapping(value = "/add_basket", method = RequestMethod.POST)
    public String add_basket(@RequestParam Integer id, @RequestParam Integer count, ModelMap model) {
        Session session = factory.openSession();
        BookDAO books = new BookDAO(session);
        Basket_customerDAO basket = new Basket_customerDAO(session);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        models.User user = (models.User)auth.getPrincipal();
        Basket_customer b_c = new Basket_customer(books.getById(id), user, count);
        session.getTransaction().begin();
        basket.save(b_c);
        session.getTransaction().commit();
        model.addAttribute("id", id);
        return "books";
    }
}
