package testDAO;

import DAO.BookDAO;
import DAO.OrderDAO;
import DAO.UserDAO;
import models.Book;
import models.Order;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;
import static utils.HibernateSessionFactoryUtil.getSessionFactory;

@Test(singleThreaded=true)
public class OrderTest {

    private SessionFactory sessionFactory = null;

    @BeforeMethod
    public void setUp() throws Exception {
        sessionFactory = getSessionFactory();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        sessionFactory.close();
    }

    @Test
    public void testSaveUpdateDelete() throws Exception {
        OrderDAO orders = new OrderDAO(sessionFactory);
        List<Order> l = orders.findAll();
        Assert.assertEquals(l.size(), 7 );

        Session sessiong = sessionFactory.openSession();
        UserDAO users = new UserDAO(sessiong);
        User customer = users.findAll().get(0);
        System.out.println("length=" + customer.getOrders().size() + "\n");

        Order order = new Order(customer,
                "г.Москва, Ломомносовский проспект, д.27",
                true,
                Timestamp.valueOf("2020-03-15 17:57:08"),
                Date.valueOf("2020-03-19"),
                "доставлен",
                100.0
                );
        //System.out.println("length=" + customer.getOrders().size() + "\n");
        orders.save(order);
        customer = users.findAll().get(0);
        System.out.println("length=" + customer.getOrders().size() + "\n");
        l = orders.findAll();
        Assert.assertEquals(l.size(), 8 );

        orders.delete(order);
        l = orders.findAll();
        Assert.assertEquals(l.size(), 7 );
        customer = users.findAll().get(0);
        System.out.println("length=" + customer.getOrders().size() + "\n");
        sessiong.close();


    }


     /*   books.delete(l.get(2));
        //System.out.println("length=" + l.get(1).getBasket_customerList().size() + "\n");
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
    }*/
}
