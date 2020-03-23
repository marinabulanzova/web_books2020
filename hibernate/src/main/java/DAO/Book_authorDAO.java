package DAO;
import models.Book_author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Book_authorDAO {
    private SessionFactory sessionFactory;

    public Book_authorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // при добавлении нового автора или книги, добавить связь
    public Integer save(Book_author book_author){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        book_author.getBook().addBook_authors(book_author);
        book_author.getAuthor().addBook_authors(book_author);
        Integer id = (Integer) session.save(book_author);

        session.getTransaction().commit();
        session.close();
        return id;
    }

    // удалить после того, как автора, или книгу удалили (или это cascade)
    public void delete(Book_author book_author) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        book_author.getAuthor().removeBook_authors(book_author);
        book_author.getBook().removeBook_authors(book_author);
        session.delete(book_author);

        session.getTransaction().commit();
        session.close();
    }

}
