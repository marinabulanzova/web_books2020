package DAO;

import models.Basket_customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class Basket_customerDAO {
    private SessionFactory sessionFactory;

    public Basket_customerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // добавить в корзину
    public void save(Basket_customer basket_c){
        Session session = sessionFactory.openSession();
        Transaction t1 = session.beginTransaction();
        session.save(basket_c);
        t1.commit();
        session.close();
    }
    // изменить количество экземпляров
    public void update(Basket_customer basket_c) {
        Session session = sessionFactory.openSession();
        Transaction t2 = session.beginTransaction();
        session.update(basket_c);
        t2.commit();
        session.close();
    }
    // убрать из корзины
    public void delete(Basket_customer basket_c) {
        Session session = sessionFactory.openSession();
        Transaction t3 = session.beginTransaction();
        session.delete(basket_c);
        t3.commit();
        session.close();
    }
    /* получить список книг из корзины для конкретного пользователя(или это не нужно,
       ведь они все припилены к соответствующему пользователю и мы можем получить их user.basket_customerList
    */
    /*public List<Basket_customer> findByIdUser(int id_user) {
        Session session = sessionFactory.openSession();
        String text_query = "SELECT b FROM Basket_customer b WHERE b.id_customer = " + id_user;
        return session.createQuery(text_query).getResultList();
    }*/
}
