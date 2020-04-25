package Controllers;
import DAO.*;
import models.Author;
import models.Book;
import models.Book_author;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            models.User user = (models.User)auth.getPrincipal();
            model.addAttribute("id", user.getId_user());
            model.addAttribute("admin", user.getAdmin());
        }
        model.addAttribute("BooksList", books.findAll());
        return "books";
    }

    @RequestMapping(value = "/search_books", method = RequestMethod.GET)
    public String findAll(HttpServletRequest request, @RequestParam String title, @RequestParam String genre,
                          @RequestParam String publishing_house,
                          @RequestParam String min_p_year, @RequestParam String max_p_year,
                          @RequestParam String min_p_count, @RequestParam String max_p_count,
                          @RequestParam String count, @RequestParam String cover,
                          @RequestParam String min_price, @RequestParam String max_price,
                          @RequestParam String name_author, ModelMap model) {
        Session session = factory.openSession();
        BookDAO books = new BookDAO(session);
        if (request.getUserPrincipal() != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            models.User user = (models.User)auth.getPrincipal();
            model.addAttribute("admin", user.getAdmin());
        }
        if (title.equals("")) title = null;
        if (genre.equals("")) genre = null;
        if (publishing_house.equals("")) publishing_house = null;
        Integer min_p_y = (min_p_year.equals("")) ? null : Integer.parseInt(min_p_year);
        Integer max_p_y = (max_p_year.equals("")) ? null : Integer.parseInt(max_p_year);
        Integer min_p_c = (min_p_count.equals("")) ? null : Integer.parseInt(min_p_count);
        Integer max_p_c = (max_p_count.equals("")) ? null : Integer.parseInt(max_p_count);
        Double min_p = (min_price.equals("")) ? null : Double.parseDouble(min_price);
        Double max_p = (max_price.equals("")) ? null : Double.parseDouble(max_price);
        Integer c = (count.equals("")) ? null : Integer.parseInt(count);
        if (cover.equals(""))  cover = null;
        if (name_author.equals("")) name_author = null;

        model.addAttribute("BooksList",
                books.find(title, genre, publishing_house, min_p_y, max_p_y, min_p_c, max_p_c,
                        c, cover, min_p, max_p, name_author));
        model.addAttribute("title", title);
        model.addAttribute("genre", genre);
        model.addAttribute("publishing_house", publishing_house);
        model.addAttribute("min_p_year", min_p_year);
        model.addAttribute("max_p_year", max_p_year);
        model.addAttribute("min_p_count", min_p_count);
        model.addAttribute("max_p_count", max_p_count);
        model.addAttribute("min_price", min_price);
        model.addAttribute("max_price", max_price);
        model.addAttribute("count", count);
        model.addAttribute("cover", cover);
        model.addAttribute("name_author", name_author);
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
        int size = book.getBook_authors().size();
        int i = 0;
        while (i < size) {
            model.addAttribute("author" + i, book.getBook_authors().get(i).getAuthor().getName());
            i++;
        }
        return "books/edit";
    }

    @RequestMapping(value = "/edit_done", method = RequestMethod.POST)
    public String edit_done(HttpServletRequest request, @RequestParam  Integer id,
                            @RequestParam String title, @RequestParam String genre,
                            @RequestParam String publishing_house, @RequestParam Integer publication_year,
                            @RequestParam String page_count, @RequestParam String count_book,
                            @RequestParam String cover, @RequestParam String price, @RequestParam String author0,
                            @RequestParam String author1, @RequestParam String author2, ModelMap model) {
        Session session = factory.openSession();
        BookDAO books = new BookDAO(session);
        if (title.equals("") || author0.equals("") || price.equals("")) {
            model.addAttribute("error", true);
            model.addAttribute("title", title);
            model.addAttribute("genre", genre);
            model.addAttribute("publishing_house", publishing_house);
            model.addAttribute("publication_year", publication_year);
            model.addAttribute("page_count", page_count);
            model.addAttribute("cover", cover);
            model.addAttribute("price", price);
            model.addAttribute("authors0", author0);
            model.addAttribute("authors1", author0);
            model.addAttribute("authors2", author0);
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
            book.setPage_count((page_count.equals("")) ? null : Integer.parseInt(page_count));
            book.setCount_book((count_book.equals("")) ? null : Integer.parseInt(count_book));
            book.setCover(cover);
            book.setPrice((price.equals("")) ? null : Double.parseDouble(price));
        } else {
            book = new Book(genre, title, publishing_house, publication_year,
                    (page_count.equals("")) ? null : Integer.parseInt(page_count),
                    (count_book.equals("")) ? null : Integer.parseInt(count_book), cover,
                    (price.equals("")) ? null : Double.parseDouble(price));
            session.getTransaction().begin();
            books.save(book);
            session.getTransaction().commit();
        }
        List<Author> author;
        Author a;
        Book_authorDAO book_authors_dao = new Book_authorDAO(session);
        AuthorDAO authors_dao = new AuthorDAO(session);
        if (id != null) {
            List<Book_author> list = book_authors_dao.find(book.getId_book());
            for (Book_author l : list) {
                session.getTransaction().begin();
                book_authors_dao.delete(l);
                session.getTransaction().commit();
            }
        }
        List<String> authors = new ArrayList<>();
        if (!author0.equals("")) authors.add(author0);
        if (!author1.equals("")) authors.add(author1);
        if (!author2.equals("")) authors.add(author2);
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
            if (!book.getBook_authors().contains(b_a)) {
                session.getTransaction().begin();
                book_authors_dao.save(b_a);
                session.getTransaction().commit();
            }
        }
        session.getTransaction().begin();
        if(id != null) {
            books.update(book);
        } else {
            books.save(book);
        }
        session.getTransaction().commit();
        //model.addAttribute("BooksList", books.findAll());
        model.addAttribute("book", book);
        if (request.getUserPrincipal() != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            models.User user = (models.User)auth.getPrincipal();
            model.addAttribute("admin", user.getAdmin());
        }
        return "books/detailed";
    }

    @RequestMapping(value = "/rm_books", method = RequestMethod.POST)
    public String remove_book(HttpServletRequest request, @RequestParam Integer id,
                                ModelMap model) {
        Session session = factory.openSession();
        BookDAO books = new BookDAO(session);

        session.getTransaction().begin();
        books.delete(books.getById(id));
        session.getTransaction().commit();

        model.addAttribute("BooksList", books.findAll());
        if (request.getUserPrincipal() != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            models.User user = (models.User)auth.getPrincipal();
            model.addAttribute("id", user.getId_user());
            model.addAttribute("admin", user.getAdmin());
        }
        return "books";
    }

    @RequestMapping(value = "/detailed_books", method = RequestMethod.GET)
    public String detailed_book(HttpServletRequest request, @RequestParam Integer id,
                                ModelMap model) {
        Session session = factory.openSession();
        BookDAO books = new BookDAO(session);
        Book book = books.getById(id);
        /*model.addAttribute("id", id);
        model.addAttribute("title", book.getTitle());
        model.addAttribute("genre", book.getGenre());
        model.addAttribute("publishing_house", book.getPublishing_house());
        model.addAttribute("publication_year", book.getPublication_year());
        model.addAttribute("page_count", book.getPage_count());
        model.addAttribute("count_book", book.getCount_book());
        model.addAttribute("cover", book.getCover());
        model.addAttribute("price", book.getPrice());
        model.addAttribute("book_authors", book.getBook_authors());*/
        model.addAttribute("book", book);
        if (request.getUserPrincipal() != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            models.User user = (models.User)auth.getPrincipal();
            model.addAttribute("admin", user.getAdmin());
        }
        return "books/detailed";
    }

}
