package Controllers;
import DAO.*;
import models.Author;
import models.Book;
import models.Book_author;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.ArrayList;

@Controller
public class BookController {
    @Autowired
    SessionFactory factory;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String findAll(ModelMap model) {
        Session session = factory.openSession();
        BookDAO books = new BookDAO(session);
        model.addAttribute("BooksList", books.findAll());
        return "books";
    }

    @RequestMapping(value = "/books/search", method = RequestMethod.GET)
    public String findAll(@RequestParam String title, @RequestParam String genre,
                          @RequestParam String publishing_house,
                          @RequestParam String min_p_year, @RequestParam String max_p_year,
                          @RequestParam String min_p_count, @RequestParam String max_p_count,
                          @RequestParam String count, @RequestParam String cover,
                          @RequestParam String min_price, @RequestParam String max_price,
                          @RequestParam String name_author, ModelMap model) {
        Session session = factory.openSession();
        BookDAO books = new BookDAO(session);

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

    @RequestMapping(value = "/books/add", method = RequestMethod.GET)
    public String book_add(ModelMap model) {
        return "books/add";
    }

    @RequestMapping(value = "/books/edit", method = RequestMethod.POST)
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

    @RequestMapping(value = "/books/edit_done", method = RequestMethod.POST)
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
            session.save(book);
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

    @RequestMapping(value = "/books/rm", method = RequestMethod.POST)
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

    @RequestMapping(value = "/books/detailed", method = RequestMethod.GET)
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
}
