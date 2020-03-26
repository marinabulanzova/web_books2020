package testDAO;
import DAO.Basket_customerDAO;
import DAO.BookDAO;
import DAO.UserDAO;
import models.Basket_customer;
import models.Book;
import models.User;
import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import static utils.HibernateSessionFactoryUtil.getSessionFactory;

public class Basket_customerTest {
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
    public void testSaveDeleteBasket_customer() throws Exception {
        Basket_customerDAO baskets = new Basket_customerDAO(session);
        UserDAO users = new UserDAO(session);
        BookDAO books = new BookDAO(session);
        User customer = users.getById(3);
        List<Basket_customer> l = customer.getBasket_customerList();
        Assert.assertEquals(l.size(), 2 );
        Book book = books.getById(2);
        Basket_customer b_c = new Basket_customer(book, customer, 1 );

        session.getTransaction().begin();
        baskets.save(b_c);
        session.getTransaction().commit();

        customer = users.getById(3);
        l = customer.getBasket_customerList();
        Assert.assertEquals(l.size(), 3 );

        session.getTransaction().begin();
        baskets.delete(b_c);
        session.getTransaction().commit();

        customer = users.getById(3);
        l = customer.getBasket_customerList();
        Assert.assertEquals(l.size(), 2 );
    }
}
