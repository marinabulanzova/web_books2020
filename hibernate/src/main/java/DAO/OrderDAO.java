package DAO;

import models.Order;
//import utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class OrderDAO {
    private SessionFactory sessionFactory;

    public Order getById(int id){
        Session session = sessionFactory.openSession();
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Order order = (Order) session.get(Order.class, id);
        session.close();
        return order;
    }

    public void save(Order order){
        Session session = sessionFactory.openSession();
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t1 = session.beginTransaction();
        session.save(order);
        t1.commit();
        session.close();
    }

    public void update(Order order) {
        Session session = sessionFactory.openSession();
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t2 = session.beginTransaction();
        session.update(order);
        t2.commit();
        session.close();
    }

    public void delete(Order order) {
        Session session = sessionFactory.openSession();
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t3 = session.beginTransaction();
        session.delete(order);
        t3.commit();
        session.close();
    }

    public List<Order> findAll() {
        Session session = sessionFactory.openSession();
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<Order> list = (List<Order>)session.createQuery("From Order").list();
        session.close();
        return list;
    }
}
