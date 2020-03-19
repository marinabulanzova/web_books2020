package DAO;
import models.Book_author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class Book_authorDAO {
    private SessionFactory sessionFactory;
    // при добавлении нового автора или книги, добавить связь
    public void save(Book_author book_author){
        Session session = sessionFactory.openSession();
        Transaction t1 = session.beginTransaction();
        session.save(book_author);
        t1.commit();
        session.close();
    }
    //как-то странно делать update типа легче уже удалить и вставить
    public void update(Book_author book_author) {
        Session session = sessionFactory.openSession();
        Transaction t2 = session.beginTransaction();
        session.update(book_author);
        t2.commit();
        session.close();
    }
    // удалить после того, как автора, или книгу удалили (или это cascade)
    public void delete(Book_author book_author) {
        Session session = sessionFactory.openSession();
        Transaction t3 = session.beginTransaction();
        session.delete(book_author);
        t3.commit();
        session.close();
    }
    // смысла получить всё тоже не вижу
    public List<Book_author> findAll() {
        Session session = sessionFactory.openSession();
        List<Book_author> list = (List<Book_author>)session.createQuery("From Author").list();
        session.close();
        return list;
    }
}
