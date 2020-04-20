package DAO;
import models.Book_author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Book_authorDAO {
    @Autowired
    private SessionFactory sessionFactory;

    /*private Session session;*/

    /*public Book_authorDAO(Session session) {
        this.session = session;
    }*/

    public Book_authorDAO() {
    }

    public Integer save(Book_author book_author){
        Session session = sessionFactory.openSession();
        book_author.getBook().addBook_authors(book_author);
        book_author.getAuthor().addBook_authors(book_author);
        Integer id = (Integer) session.save(book_author);
        session.close();
        return id;
    }

    public void delete(Book_author book_author) {
        Session session = sessionFactory.openSession();
        session.delete(book_author);
        book_author.getAuthor().removeBook_authors(book_author);
        book_author.getBook().removeBook_authors(book_author);
        session.close();
    }

}
