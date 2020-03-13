package DAO;

import models.Author;
import models.Book;
//import utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class BookDAO {
    private SessionFactory sessionFactory;

    public Book getById(int id){
        Session session = sessionFactory.openSession();
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Book book = (Book) session.get(Book.class, id);
        session.close();
        return book;
    }

    public void save(Book book){
        Session session = sessionFactory.openSession();
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t1 = session.beginTransaction();
        session.save(book);
        t1.commit();
        session.close();
    }

    public void update(Book book) {
        Session session = sessionFactory.openSession();
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t2 = session.beginTransaction();
        session.update(book);
        t2.commit();
        session.close();
    }

    public void delete(Book book) {
        Session session = sessionFactory.openSession();
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t3 = session.beginTransaction();
        session.delete(book);
        t3.commit();
        session.close();
    }

    public List<Book> findAll() {
        Session session = sessionFactory.openSession();
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<Book> list = (List<Book>)session.createQuery("From Books").list();
        session.close();
        return list;
    }

    public Book find_by_title(String title) {
        Session session = sessionFactory.openSession();
        List<Book> books = (List<Book>)session.createQuery("SELECT b FROM Books b WHERE b.title = :title").list();
        session.close();
        return books;
    }
    public Book find_by_price (int min, int max) {
        Session session = sessionFactory.openSession();
        List<Book> list = (List<Book>)session.createQuery("SELECT b FROM Books b WHERE b.price BETWEEN :min AND :max").list();
        session.close();
        return list;
    }

    public Book find_by_price (String title) {
        Session session = sessionFactory.openSession();
        List<Book> list = (List<Book>)session.createQuery("SELECT b FROM Books b WHERE b.genre = :genre").list();
        session.close();
        return list;
    }

}
