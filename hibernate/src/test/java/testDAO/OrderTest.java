package testDAO;

import DAO.OrderDAO;
import DAO.UserDAO;
import models.Order;
import models.User;
import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import static utils.HibernateSessionFactoryUtil.getSessionFactory;

public class OrderTest {
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
    public void testSaveUpdateDeleteOrder() throws Exception {
        OrderDAO orders = new OrderDAO(session);
        List<Order> l = orders.findAll();
        Assert.assertEquals(l.size(), 7 );

        UserDAO users = new UserDAO(session);
        User customer = users.findAll().get(0);

        Order order = new Order(customer,
                "г.Москва, Ломоносовский проспект, д.27",
                true,
                Timestamp.valueOf("2020-03-15 17:57:08"),
                Date.valueOf("2020-03-19"),
                "доставлен",
                100.0
                );

        session.getTransaction().begin();
        int id_order  = orders.save(order);
        session.getTransaction().commit();

        l = orders.findAll();
        Assert.assertEquals(l.size(), 8 );
        Assert.assertEquals(orders.getById(id_order).getCustomer(), customer);
        Assert.assertEquals((boolean) orders.getById(id_order).getPayment(), true);
        Assert.assertEquals(orders.getById(id_order).getDelivery_address(), "г.Москва, Ломоносовский проспект, д.27");
        Assert.assertEquals(orders.getById(id_order).getOrder_date(), Timestamp.valueOf("2020-03-15 17:57:08"));
        Assert.assertEquals(orders.getById(id_order).getDelivery_date(), Date.valueOf("2020-03-19"));
        Assert.assertEquals(orders.getById(id_order).getStatus(), "доставлен" );
        Assert.assertEquals(orders.getById(id_order).getDelivery_price(), 100.0);

        session.getTransaction().begin();
        orders.delete(order);
        session.getTransaction().commit();

        l = orders.findAll();
        Assert.assertEquals(l.size(), 7 );
        Assert.assertEquals(l.get(3).getStatus(), "в обработке");
        Assert.assertEquals((int)l.get(3).getId_order(), 2);
        order = orders.getById(2);
        order.setStatus("собран");
        orders.update(order);
        l = orders.findAll();
        Assert.assertEquals(l.get(3).getStatus(), "собран");
        order = l.get(3);
        order.setStatus("в обработке");
        orders.update(order);
    }

    @Test(priority = 0)
    public void testFindOrder() throws Exception {
        OrderDAO orders = new OrderDAO(session);
        UserDAO users = new UserDAO(session);

        List<Order> l = orders.find(2,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        Assert.assertEquals(l.size(), 2);
        Assert.assertEquals((int)l.get(0).getCustomer().getId_user(), 2);
        Assert.assertEquals((int)l.get(1).getCustomer().getId_user(), 2);

        l = orders.find(null,
                "3 почтовое отделение",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        Assert.assertEquals(l.size(), 0);

        l = orders.find(null,
                null,
                null,
                null,
                null,
                Date.valueOf("2020-01-25"),
                Date.valueOf("2020-02-02"),
                null,
                null,
                null);
        Assert.assertEquals(l.size(), 2);
        Assert.assertEquals(l.get(0).getDelivery_date(), Date.valueOf("2020-01-26"));
        Assert.assertEquals(l.get(1).getDelivery_date(), Date.valueOf("2020-02-01"));

        l = orders.find(null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                10.0,
                120.0);
        Assert.assertEquals(l.size(), 1);
        Assert.assertEquals(l.get(0).getDelivery_price(),100.0);
    }

}
