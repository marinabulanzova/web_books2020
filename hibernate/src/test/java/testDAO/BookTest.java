package testDAO;

import DAO.BookDAO;
import models.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static utils.HibernateSessionFactoryUtil.getSessionFactory;

@Test(singleThreaded=true)
public class BookTest {

    private SessionFactory sessionFactory = null;
    private Session session = null;

    @BeforeMethod
    public void setUp() throws Exception {
        sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();

    }
    @AfterMethod
    public void tearDown() throws Exception {
        session.close();
        sessionFactory.close();
    }

    @Test
    public void testSaveListDelete() throws Exception {
        BookDAO books = new BookDAO(sessionFactory);
        session.getTransaction().begin();
        List<Book> l = books.findAll();
        Assert.assertEquals(l.size(), 8);
        session.getTransaction().commit();

        session.getTransaction().begin();
        Book book = new Book("классика",
                "Воскресенье",
                "Просвещение",
                2016,
                467,
                4,
                "Твёрдая",
                590.0);
        books.save(book);
        session.getTransaction().commit();

        session.getTransaction().begin();
        l = books.findAll();
        Assert.assertEquals(l.size(), 9);
        session.getTransaction().commit();
        /*Assert.assertEquals(l.get(8).getTitle(), "Воскресенье");
        session.getTransaction().commit();

        session.getTransaction().begin();
        books.delete(l.get(8));
        session.getTransaction().commit();

        session.getTransaction().begin();
        l = books.findAll();
        Assert.assertEquals(l.size(), 8);
        session.getTransaction().commit();*/

    }

}
