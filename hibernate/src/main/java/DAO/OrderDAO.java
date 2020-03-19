package DAO;

import models.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

public class OrderDAO {
    private SessionFactory sessionFactory;

    public Order getById(int id){
        Session session = sessionFactory.openSession();
        Order order = (Order) session.get(Order.class, id);
        session.close();
        return order;
    }

    public void save(Order order){
        Session session = sessionFactory.openSession();
        Transaction t1 = session.beginTransaction();
        session.save(order);
        t1.commit();
        session.close();
    }

    public void update(Order order) {
        Session session = sessionFactory.openSession();
        Transaction t2 = session.beginTransaction();
        session.update(order);
        t2.commit();
        session.close();
    }

    public void delete(Order order) {
        Session session = sessionFactory.openSession();
        Transaction t3 = session.beginTransaction();
        session.delete(order);
        t3.commit();
        session.close();
    }

    public List<Order> findAll() {
        Session session = sessionFactory.openSession();
        List<Order> list = (List<Order>)session.createQuery("From Order").list();
        session.close();
        return list;
    }

    // поиск заказа по различным фильтрам
    public List<Order> find(int id_customer, String delivery_address, String payment, Date min_o_date, Date max_o_date,
                            Date min_d_date, Date max_d_date, String status, double min_d_price, double max_d_price) {
        Session session = sessionFactory.openSession();
        String text_query = "SELECT o FROM Order o";
        if (id_customer != 0 || delivery_address != null || payment != null ||
                min_o_date != null || max_o_date != null || min_d_date != null || max_d_date != null ||
                status != null || min_d_price != 0 || max_d_price != 0) {
            text_query += " WHERE";
            Boolean flagAnd = false;
            if (id_customer != 0) {
                text_query += (flagAnd ? " AND" : "") + " o.id_customer = " + id_customer;
                flagAnd = true;
            }
            if (delivery_address != null) {
                text_query += (flagAnd ? " AND" : "") + " o.delivery_address = '" + delivery_address + "'";
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
            if (min_d_price != 0) {
                text_query += (flagAnd ? " AND" : "") + " o.delivery_price >= " + min_d_price;
                flagAnd = true;
            }
            if (max_d_price != 0) {
                text_query += (flagAnd ? " AND" : "") + " o.delivery_price = " + max_d_price;
                flagAnd = true;
            }
        }
        return session.createQuery(text_query).getResultList();
    }
}
