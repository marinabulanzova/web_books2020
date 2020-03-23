package DAO;

import models.Author;
import org.hibernate.Session;

import java.util.List;

public class AuthorDAO {
    private Session session;

    public AuthorDAO(Session session) {
        this.session = session;
    }

    public Author getById(int id) {
        Author author = (Author) session.get(Author.class, id);
        return author;
    }

    public Integer save(Author author) {
        Integer id = (Integer) session.save(author);
        return id;
    }

    public void delete(Author author) {
        session.delete(author);
    }

    public List<Author> findAll() {
        String test_query = "SELECT a FROM Author a order by name";
        List<Author> list = (List<Author>)session.createQuery(test_query).getResultList();
        return list;
    }
}
