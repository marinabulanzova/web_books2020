package DAO;

import models.Book;
import models.Order;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

public class OrderDAO {
    private SessionFactory sessionFactory;

    public OrderDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Order getById(int id){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Order order = (Order) session.get(Order.class, id);

        session.getTransaction().commit();
        session.close();
        return order;
    }

    public Integer save(Order order){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        order.getCustomer().addOrder(order);
        Integer id = (Integer) session.save(order);

        session.getTransaction().commit();
        session.close();
        return id;
    }

    public void update(Order order) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        //order.getCustomer().removeOrder(order);
        session.update(order);
        //order.getCustomer().addOrder(order);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Order order) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        order.getCustomer().removeOrder(order);
        session.delete(order);

        session.getTransaction().commit();
        session.close();
    }

    public List<Order> findAll() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        String test_query = "SELECT o FROM Order o ORDER BY order_date";
        List<Order> list = (List<Order>)session.createQuery(test_query).getResultList();

        session.getTransaction().commit();
        session.close();
        return list;
    }

    // поиск заказа по различным фильтрам
    public List<Order> find(User customer, String delivery_address, Boolean payment_card, Date min_o_date, Date max_o_date,
                            Date min_d_date, Date max_d_date, String status, Double min_d_price, Double max_d_price) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        String text_query = "SELECT o FROM Order o";
        if (customer != null || delivery_address != null || payment_card != null ||
                min_o_date != null || max_o_date != null || min_d_date != null || max_d_date != null ||
                status != null || min_d_price != 0 || max_d_price != 0) {
            text_query += " WHERE";
            Boolean flagAnd = false;
            if (customer != null) {
                text_query += (flagAnd ? " AND" : "") + " o.customer.id_customer = " + customer;
                flagAnd = true;
            }
            if (delivery_address != null) {
                text_query += (flagAnd ? " AND" : "") + " o.delivery_address = '" + delivery_address + "'";
                flagAnd = true;
            }
            if (payment_card != null) {
                text_query += (flagAnd ? " AND" : "") + " o.payment_card = '" + payment_card + "'";
                flagAnd = true;
            }
            if (min_o_date != null) {
                text_query += (flagAnd ? " AND" : "") + " DATE(o.order_date) >= '" + min_o_date + "'";
                flagAnd = true;
            }
            if (max_o_date != null) {
                text_query += (flagAnd ? " AND" : "") + " DATE(o.order_date) <= '" + max_o_date + "'";
                flagAnd = true;
            }
            if (min_d_date != null) {
                text_query += (flagAnd ? " AND" : "") + " o.order_date >= '" + min_d_date + "'";
                flagAnd = true;
            }
            if (max_d_date != null) {
                text_query += (flagAnd ? " AND" : "") + " o.order_date = '" + max_d_date + "'";
                flagAnd = true;
            }
            if (status != null) {
                text_query += (flagAnd ? " AND" : "") + " o.status  = '" + status + "'";
                flagAnd = true;
            }
            if (min_d_price != null) {
                text_query += (flagAnd ? " AND" : "") + " o.delivery_price >= " + min_d_price;
                flagAnd = true;
            }
            if (max_d_price != null) {
                text_query += (flagAnd ? " AND" : "") + " o.delivery_price = " + max_d_price;
                flagAnd = true;
            }
        }
        text_query+=" ORDER BY order_date";
        List<Order> list = (List<Order>)session.createQuery(text_query).getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
