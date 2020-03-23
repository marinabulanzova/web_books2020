package DAO;

import models.Order;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class UserDAO {
    private SessionFactory sessionFactory;
    private  Session sessiong;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public UserDAO(Session sessiong) {
        this.sessiong = sessiong;
    }

    public User getById(int id){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        User user = (User) session.get(User.class, id);

        session.getTransaction().commit();
        session.close();
        return user;
    }

    public Integer save(User user){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Integer id = (Integer) session.save(user);

        session.getTransaction().commit();
        session.close();
        return id;
    }

    public void update(User user) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        session.update(user);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(User user) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        session.delete(user);

        session.getTransaction().commit();
        session.close();
    }

    public List<User> findAll() {
        //Session session = sessionFactory.openSession();
        //session.getTransaction().begin();

        String test_query = "SELECT u FROM User u ORDER BY surname";
        List<User> list = (List<User>)sessiong.createQuery(test_query).getResultList();

        //session.getTransaction().commit();
        //session.close();
        return list;
    }

// поиск клиента по различным фильтрам
    public List<User> find(String surname, String first_name, String patronymic, String address, String phone_number,
                           String e_mail, Boolean admin) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        String text_query = "SELECT u FROM User u";
        if (surname  != null || first_name != null || patronymic != null || address != null ||
                phone_number != null || e_mail != null || admin != null) {
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
                text_query += (flagAnd ? " AND":"") + " u.address LIKE '%" + address    + "%'";
                flagAnd = true;
            }
            if (phone_number != null) {
                text_query += (flagAnd ? " AND":"") + " u.phone_number  = '" + phone_number  + "'";
                flagAnd = true;
            }
            if (e_mail != null) {
                text_query += (flagAnd ? " AND":"") + " u.e_mail = '" + e_mail + "'";
                flagAnd = true;
            }
            if (admin != null) {
                text_query += (flagAnd ? " AND":"") +" u.admin = " + admin;
                flagAnd = true;
            }
        }
        text_query+=" ORDER BY surname";
        System.out.println(text_query);
        List<User> list = (List<User>)session.createQuery(text_query).getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }

}
