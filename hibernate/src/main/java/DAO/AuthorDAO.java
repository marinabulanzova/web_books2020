package DAO;

import models.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class AuthorDAO {
    private SessionFactory sessionFactory;

    public Author getById(int id){
        Session session = sessionFactory.openSession();
        Author author = (Author) session.get(Author.class, id);
        session.close();
        return author;
    }

    public void save(Author author){
        Session session = sessionFactory.openSession();
        Transaction t1 = session.beginTransaction();
        session.save(author);
        t1.commit();
        session.close();
    }

    public void update(Author author) {
        Session session = sessionFactory.openSession();
        Transaction t2 = session.beginTransaction();
        session.update(author);
        t2.commit();
        session.close();
    }

    public void delete(Author author) {
        Session session = sessionFactory.openSession();
        Transaction t3 = session.beginTransaction();
        session.delete(author);
        t3.commit();
        session.close();
    }

    public List<Author> findAll() {
        Session session = sessionFactory.openSession();
        List<Author> list = (List<Author>)session.createQuery("From Author").list();
        session.close();
        return list;
    }

}
