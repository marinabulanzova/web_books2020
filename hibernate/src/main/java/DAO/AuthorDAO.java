package DAO;

import models.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuthorDAO {

    /*private Session session;

    public AuthorDAO(Session session) {
        this.session = session;
    }*/
    @Autowired
    private SessionFactory sessionFactory;

    public Author getById(int id) {
        Session session = sessionFactory.openSession();
        Author author = (Author) session.get(Author.class, id);
        session.close();
        return author;
    }

    public Integer save(Author author) {
        Session session = sessionFactory.openSession();
        Integer id = (Integer) session.save(author);
        session.close();
        return id;
    }

    public void delete(Author author) {
        Session session = sessionFactory.openSession();
        session.delete(author);
        session.close();
    }

    public List<Author> findAll() {
        String test_query = "SELECT a FROM Author a order by name";
        Session session = sessionFactory.openSession();
        List<Author> list = (List<Author>)session.createQuery(test_query).getResultList();
        session.close();
        return list;
    }

    public List<Author> find(String name) {
        Session session = sessionFactory.openSession();
        String test_query = "SELECT a FROM Author a where name = '" +  name + "' order by name";
        List<Author> list = (List<Author>)session.createQuery(test_query).getResultList();
        session.close();
        return list;
    }


}
