package DAO;

import models.Basket_order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class Basket_orderDAO {
    private SessionFactory sessionFactory;
    // добавить в корзину заказа (происходит после этапа добавления заказа, и копируется из корзины пользователя)
    public void save(Basket_order basket_o){
        Session session = sessionFactory.openSession();
        Transaction t1 = session.beginTransaction();
        session.save(basket_o);
        t1.commit();
        session.close();
    }
    // кажется что update не нужен, мы же не можем изменить цену и количество товара, после того, как заказ был оформлен
    /*public void update(Basket_order basket_o) {
        Session session = sessionFactory.openSession();
        Transaction t2 = session.beginTransaction();
        session.update(basket_o);
        t2.commit();
        session.close();
    }*/
    // удалять после того, как удалили соответствующий заказ (или это не нужно так-как удаление каскадное ?)
    public void delete(Basket_order basket_o) {
        Session session = sessionFactory.openSession();
        Transaction t3 = session.beginTransaction();
        session.delete(basket_o);
        t3.commit();
        session.close();
    }

    // удалить по id заказа (или это не нужно так-как удаление каскадное ?)
    public int deleteByIdOrder(int id_order) {
        Session session = sessionFactory.openSession();
        String text_query = "DELETE Basket_order WHERE id_order = " + id_order;
        return session.createQuery(text_query).executeUpdate();
    }
    // аналогично basket_customer нет смысла искать все книги и для конкретного пользователя тоже
    /*public List<Basket_order> find() {
        Session session = sessionFactory.openSession();
        List<Basket_order> list = (List<Basket_order>)session.createQuery("From Author").list();
        session.close();
        return list;
    }*/
}
