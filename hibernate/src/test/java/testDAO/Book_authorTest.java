package testDAO;
import DAO.*;
import models.*;
import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import static utils.HibernateSessionFactoryUtil.getSessionFactory;

public class Book_authorTest {
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
    public void testSaveDeleteBook_author() throws Exception {
        Book_authorDAO authors_books = new Book_authorDAO(session);
        AuthorDAO authors = new AuthorDAO(session);
        Author author = authors.getById(9);
        BookDAO books = new BookDAO(session);
        Book book = books.getById(8);
        List<Book_author> l = book.getBook_authors();
        Assert.assertEquals(l.size(), 1 ); //6
        Book_author b_a = new Book_author(book, author);

        session.getTransaction().begin();
        authors_books.save(b_a);
        session.getTransaction().commit();

        book = books.getById(8);
        l = book.getBook_authors();
        Assert.assertEquals(l.size(), 2);

        session.getTransaction().begin();
        authors_books.delete(b_a);
        session.getTransaction().commit();

        book = books.getById(8);
        l = book.getBook_authors();
        Assert.assertEquals(l.size(), 1);
    }

}
