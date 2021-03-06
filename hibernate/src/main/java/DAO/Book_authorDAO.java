package DAO;
import models.Book;
import models.Book_author;
import org.hibernate.Session;

import java.util.List;

public class Book_authorDAO {

    private Session session;

    public Book_authorDAO(Session session) {
        this.session = session;
    }

    public Integer save(Book_author book_author){
        book_author.getBook().addBook_authors(book_author);
        book_author.getAuthor().addBook_authors(book_author);
        Integer id = (Integer) session.save(book_author);
        return id;
    }

    public void delete(Book_author book_author) {
        session.delete(book_author);
        book_author.getAuthor().removeBook_authors(book_author);
        book_author.getBook().removeBook_authors(book_author);
    }

    public List<Book_author> find(Integer id_book) {
        String test_query = "SELECT b FROM Book_author b WHERE b.book.id_book = " + id_book;
        List<Book_author> list = (List<Book_author>)session.createQuery(test_query).getResultList();
        return list;
    }
}
