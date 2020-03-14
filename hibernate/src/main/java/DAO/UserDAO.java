package DAO;

import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.TypedQuery;
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
        Transaction t1 = session.beginTransaction();
        session.save(user);
        t1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = sessionFactory.openSession();
        Transaction t2 = session.beginTransaction();
        session.update(user);
        t2.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction t3 = session.beginTransaction();
        session.delete(user);
        t3.commit();
        session.close();
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        List<User> list = (List<User>)session.createQuery("From User").list();
        session.close();
        return list;
    }

/*    public List<User> find_by_name(String first_name, String surname) {
        Session session = sessionFactory.openSession();
        String query = "SELECT u from User u where u.first_name = :first_name and u.surname = :surname";
        TypedQuery<User> q = session.createQuery(query, User.class);
        q.setParameter("first_name", first_name);
        q.setParameter("surname", surname);
        return q.getResultList();
    }

    public List<User> find_by_e_mail(String e_mail) {
        Session session = sessionFactory.openSession();
        String query = "SELECT u from User u where u.e_mail = :e_mail";
        TypedQuery<User> q = session.createQuery(query, User.class);
        q.setParameter("e_mail", e_mail);
        return q.getResultList();
    }

    public List<User> find_by_phone_number(String phone_number) {
        Session session = sessionFactory.openSession();
        String query = "SELECT u from User u where u.phone_number = :phone_number";
        TypedQuery<User> q = session.createQuery(query, User.class);
        q.setParameter("phone_number", phone_number);
        return q.getResultList();
    }*/

// поиск клиента по различным фильтрам
    public List<User> find(String surname, String first_name, String patronymic, String address, String phone_number, String e_mail, Boolean status) {
        Session session = sessionFactory.openSession();
        String text_query = "SELECT u FROM User u";
        if (surname  != null ||
                first_name != null ||
                patronymic != null ||
                address != null ||
                phone_number != null ||
                e_mail != null ||
                status != null)
            text_query += " WHERE";
        Boolean needAnd = false;
        if (surname != null) {
            text_query += (needAnd ? " AND":"")+" u.surname = '" + surname  + "'"; needAnd = true;
        }
        if (first_name != null) {
            text_query += (needAnd ? " AND":"") + " u.first_name = '" + first_name  + "'"; needAnd = true;
        }
        if (patronymic != null) {
            text_query += (needAnd ? " AND":"") + " u.patronymic = '" + patronymic + "'"; needAnd = true;
        }
        if (address != null) {
            text_query += (needAnd ? " AND":"") + " u.address    = '" + address    + "'"; needAnd = true;
        }
        if (phone_number != null) {
            text_query += (needAnd ? " AND":"") + " u.phone_number  = '" + phone_number  + "'"; needAnd = true;
        }
        if (e_mail != null) {
            text_query += (needAnd ? " AND":"") + " c.email = '" + e_mail + "'"; needAnd = true;
        }
        if (status != null) {
            text_query += (needAnd ? " AND":"") +" c.status = '" + status + "'"; needAnd = true;
        }
        return session.createQuery(text_query).getResultList();
    }

}
