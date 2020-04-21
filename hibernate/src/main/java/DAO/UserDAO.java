package DAO;

import models.Order;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;

import java.util.List;


public class UserDAO {
    private  Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public UserDAO() {
    }

    public User getById(int id){
        User user = (User) session.get(User.class, id);
        return user;

    }

    public Integer save(User user){
        Integer id = (Integer) session.save(user);
        return id;
    }

    public void update(User user) {
        session.update(user);
    }

    public void delete(User user) {
        session.remove(user);
    }

    public List<User> findAll() {
        String test_query = "SELECT u FROM User u ORDER BY surname";
        List<User> list = (List<User>)session.createQuery(test_query).getResultList();
        return list;
    }

// поиск клиента по различным фильтрам
    public List<User> find(String surname, String first_name, String patronymic, String address, String phone_number,
                           String e_mail, Boolean admin) {

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
        List<User> list = (List<User>)session.createQuery(text_query).getResultList();
        return list;
    }

}
