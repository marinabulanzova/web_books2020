package DAO;

import models.Basket_order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class Basket_orderDAO {
    private SessionFactory sessionFactory;

    public Basket_orderDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // добавить в корзину заказа (происходит после этапа добавления заказа, и копируется из корзины пользователя)
    public Integer save(Basket_order basket_o){
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();

        basket_o.getOrder().addBasket_orderList(basket_o);
        basket_o.getBook().addBasket_orderList(basket_o);
        Integer id = (Integer) session.save(basket_o);

        t.commit();
        session.close();
        return id;
    }

    // удалять после того, как удалили соответствующий заказ (или это не нужно так-как удаление каскадное ?)
    public void delete(Basket_order basket_o) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();

        basket_o.getBook().removeBasket_orderList(basket_o);
        basket_o.getOrder().removeBasket_orderList(basket_o);
        session.delete(basket_o);

        t.commit();
        session.close();
    }

}
