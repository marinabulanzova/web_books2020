package DAO;

import models.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class AuthorDAO {
    private SessionFactory sessionFactory;

    public AuthorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Author getById(int id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Author author = (Author) session.get(Author.class, id);

        session.close();
        return author;
    }

    // добавление автора только в том случае, когда создаём книгу с таким автором
    public Integer save(Author author) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Integer id = (Integer) session.save(author);

        session.getTransaction().commit();
        session.close();
        return id;
    }

    public void update(Author author) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        session.update(author);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Author author) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        session.delete(author);

        session.getTransaction().commit();
        session.close();
    }

}
