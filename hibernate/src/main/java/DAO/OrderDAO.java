package DAO;

import models.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.sql.Date;
import java.sql.Timestamp;
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

    public List<Order> find_by_phone_number(Date begin, Date end) {
        Session session = sessionFactory.openSession();
        String query = "SELECT o from Order o where date(o.order_date) between :begin and :end";
        TypedQuery<Order> q = session.createQuery(query, Order.class);
        q.setParameter("begin", begin);
        q.setParameter("end", end);
        return q.getResultList();
    }
}
