package DAO;

import models.Basket_customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class Basket_customerDAO {
    /*private Session session;

    public Basket_customerDAO(Session session) {
        this.session = session;
    }*/
    @Autowired
    private SessionFactory sessionFactory;

    // добавить в корзину
    public Integer save(Basket_customer basket_c){
        Session session = sessionFactory.openSession();
        basket_c.getCustomer().addBasket_customerList(basket_c);
        basket_c.getBook().addBasket_customerList(basket_c);
        Integer id = (Integer) session.save(basket_c);
        session.close();
        return id;
    }

    // убрать из корзины
    public void delete(Basket_customer basket_c) {
        Session session = sessionFactory.openSession();
        session.delete(basket_c);
        basket_c.getBook().removeBasket_customerList(basket_c);
        basket_c.getCustomer().removeBasket_customerList(basket_c);
        session.close();
    }
}
