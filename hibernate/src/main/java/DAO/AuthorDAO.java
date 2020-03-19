package DAO;

import models.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class AuthorDAO {
    private SessionFactory sessionFactory;

    public Author getById(int id) {
        Session session = sessionFactory.openSession();
        Author author = (Author) session.get(Author.class, id);
        session.close();
        return author;
    }

    public void save(Author author) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.save(author);
        t.commit();
        session.close();
    }

    public void update(Author author) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.update(author);
        t.commit();
        session.close();
    }

    public void delete(Author author) {
        Session session = sessionFactory.openSession();
        Transaction t3 = session.beginTransaction();
        session.delete(author);
        t3.commit();
        session.close();
    }

    /*public List<Author> findAll() {
        Session session = sessionFactory.openSession();
        List<Author> list = (List<Author>)session.createQuery("From Author").list();
        session.close();
        return list;
    }

    public List findByName(String name) {
        Session session = sessionFactory.openSession();
        String text_query = "SELECT a FROM Author a WHERE a.name LIKE '%" + name + "%'";
        return session.createQuery(text_query).getResultList();
    }*/

}
