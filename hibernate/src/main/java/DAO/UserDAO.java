package DAO;

import models.User;
//import utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class UserDAO {
    private SessionFactory sessionFactory;

    public User getById(int id){
        Session session = sessionFactory.openSession();
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    public void save(User user){
        Session session = sessionFactory.openSession();
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t1 = session.beginTransaction();
        session.save(user);
        t1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = sessionFactory.openSession();
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t2 = session.beginTransaction();
        session.update(user);
        t2.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = sessionFactory.openSession();
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction t3 = session.beginTransaction();
        session.delete(user);
        t3.commit();
        session.close();
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        //Session session = HibernateSessionFactory.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<User> list = (List<User>)session.createQuery("From User").list();
        session.close();
        return list;
    }
}
