package DAO;

import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class UserDAO {
    private SessionFactory sessionFactory;

    public User getById(int id){
        Session session = sessionFactory.openSession();
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

// поиск клиента по различным фильтрам
    public List<User> find(String surname, String first_name, String patronymic, String address, String phone_number,
                           String e_mail, String status) {
        Session session = sessionFactory.openSession();
        String text_query = "SELECT u FROM User u";
        if (surname  != null || first_name != null || patronymic != null || address != null ||
                phone_number != null || e_mail != null || status != null) {
            text_query += " WHERE";
            Boolean flagAnd = false;
            if (surname != null) {
                text_query += (flagAnd ? " AND":"")+" u.surname = '" + surname  + "'";
                flagAnd = true;
            }
            if (first_name != null) {
                text_query += (flagAnd ? " AND":"") + " u.first_name = '" + first_name  + "'";
                flagAnd = true;
            }
            if (patronymic != null) {
                text_query += (flagAnd ? " AND":"") + " u.patronymic = '" + patronymic + "'";
                flagAnd = true;
            }
            if (address != null) {
                text_query += (flagAnd ? " AND":"") + " u.address   = '" + address    + "'";
                flagAnd = true;
            }
            if (phone_number != null) {
                text_query += (flagAnd ? " AND":"") + " u.phone_number  = '" + phone_number  + "'";
                flagAnd = true;
            }
            if (e_mail != null) {
                text_query += (flagAnd ? " AND":"") + " c.email = '" + e_mail + "'";
                flagAnd = true;
            }
            if (status != null) {
                text_query += (flagAnd ? " AND":"") +" c.status = '" + status + "'";
                flagAnd = true;
            }
        }
        return session.createQuery(text_query).getResultList();
    }

}
