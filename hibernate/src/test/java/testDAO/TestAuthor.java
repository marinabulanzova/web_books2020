package testDAO;

import DAO.AuthorDAO;
import models.Author;
import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import static utils.HibernateSessionFactoryUtil.getSessionFactory;

public class TestAuthor {
    private Session session = null;

    @BeforeMethod
    public void setUp() throws Exception {
        session = getSessionFactory().openSession();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        session.close();
    }

    @Test(priority = 1)
    public void testSaveDeleteAuthor() throws Exception {
        AuthorDAO authors = new AuthorDAO(session);
        List<Author> l = authors.findAll();
        Assert.assertEquals(l.size(), 9 );

        Author author = new Author("Николай Абрамов");

        session.getTransaction().begin();
        int id_author  = authors.save(author);
        session.getTransaction().commit();

        l = authors.findAll();
        Assert.assertEquals(l.size(), 10 );
        Assert.assertEquals(authors.getById(id_author).getName(), "Николай Абрамов");

        session.getTransaction().begin();
        authors.delete(author);
        session.getTransaction().commit();

        l = authors.findAll();
        Assert.assertEquals(l.size(), 9);
    }

}
