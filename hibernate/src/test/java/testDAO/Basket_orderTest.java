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

public class Basket_orderTest {
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
    public void testSaveDeleteBasket_order() throws Exception {
        Basket_orderDAO baskets = new Basket_orderDAO(session);
        OrderDAO orders = new OrderDAO(session);
        BookDAO books = new BookDAO(session);
        Order order = orders.getById(1);
        List<Basket_order> l = order.getBasket_orderList();
        Assert.assertEquals(l.size(), 2 );
        Book book = books.getById(4);
        Basket_order b_o = new Basket_order(book, order, 1, book.getPrice());

        session.getTransaction().begin();
        baskets.save(b_o);
        session.getTransaction().commit();

        order = orders.getById(1);
        l = order.getBasket_orderList();
        Assert.assertEquals(l.size(), 3 );

        session.getTransaction().begin();
        baskets.delete(b_o);
        session.getTransaction().commit();

        order = orders.getById(1);
        l = order.getBasket_orderList();
        Assert.assertEquals(l.size(), 2 );
    }
}
