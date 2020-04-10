package testDAO;

import DAO.*;
import models.Book;
import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static utils.HibernateSessionFactoryUtil.getSessionFactory;

public class BookTest {

    private Session session = null;

    @BeforeMethod
    public void setUp() throws Exception {
        session = getSessionFactory().openSession();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        session.close();
    }

    @Test(priority = 2)
    public void testSaveUpdateDeleteBook() throws Exception {
        BookDAO books = new BookDAO(session);
        List<Book> l = books.findAll();
        Assert.assertEquals(l.size(), 8);
        Book book = new Book("классика",
                "Воскресенье",
                "Просвещение",
                2016,
                467,
                4,
                "Твёрдая",
                370.0);

        session.getTransaction().begin();
        books.save(book);
        session.getTransaction().commit();

        l = books.findAll();
        Assert.assertEquals(l.size(), 9);
        Assert.assertEquals(l.get(2).getTitle(), "Воскресенье");
        Assert.assertEquals(l.get(2).getGenre(), "классика");
        Assert.assertEquals(l.get(2).getPublishing_house(), "Просвещение");
        Assert.assertEquals((int)l.get(2).getPublication_year(), 2016);
        Assert.assertEquals((int)l.get(2).getPage_count(), 467);
        Assert.assertEquals((int)l.get(2).getCount_book(), 4);
        Assert.assertEquals(l.get(2).getCover(), "Твёрдая");
        Assert.assertEquals(l.get(2).getPrice(), 370.0);

        session.getTransaction().begin();
        books.delete(l.get(2));
        session.getTransaction().commit();

        l = books.findAll();
        Assert.assertEquals(l.size(), 8);

        Assert.assertEquals(l.get(7).getPrice(), 1700.0);
        Assert.assertEquals((int)l.get(7).getId_book(), 1);
        book = books.getById(1);
        book.setPrice(1600.0);
        books.update(book);
        l = books.findAll();
        Assert.assertEquals(l.get(7).getPrice(), 1600.0);
        book = l.get(7);
        book.setPrice(1700.0);
        books.update(book);
    }

    @Test(priority = 0)
    public void testFindBook() throws Exception {
        BookDAO books = new BookDAO(session);
        List<Book> l = books.find(
                "Вынос дела",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        Assert.assertEquals(l.size(), 1);
        Assert.assertEquals(l.get(0).getTitle(), "Вынос дела");

        l = books.find(
                null,
                "классика",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        Assert.assertEquals(l.size(), 2);
        Assert.assertEquals(l.get(0).getGenre(), "классика");
        Assert.assertEquals(l.get(1).getGenre(), "классика");

        l = books.find(
                null,
                null,
                "Просвещение",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        Assert.assertEquals(l.size(), 2);
        Assert.assertEquals(l.get(0).getPublishing_house(), "Просвещение");
        Assert.assertEquals(l.get(1).getPublishing_house(), "Просвещение");

        l = books.find(
                null,
                null,
                null,
                2018,
                2020,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        Assert.assertEquals(l.size(), 2);
        Assert.assertEquals((int)l.get(0).getPublication_year(), 2018);
        Assert.assertEquals((int)l.get(1).getPublication_year(), 2019);

        l = books.find(
                null,
                null,
                null,
                null,
                null,
                1600,
                2000,
                null,
                null,
                null,
                null,
                null
        );
        Assert.assertEquals(l.size(), 0);

        l = books.find(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                200.0,
                450.0,
                null
        );
        Assert.assertEquals(l.size(), 3);
        Assert.assertEquals(l.get(0).getPrice(), 270.0);
        Assert.assertEquals(l.get(1).getPrice(), 360.0);
        Assert.assertEquals(l.get(2).getPrice(), 400.0);

        l = books.find(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                "Стивен Кинг"
        );
        Assert.assertEquals(l.size(), 1); //6
    }

}
