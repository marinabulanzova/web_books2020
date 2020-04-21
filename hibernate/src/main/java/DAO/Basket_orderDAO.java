package DAO;

import models.Basket_order;
import models.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Basket_orderDAO {

    private Session session;

    public Basket_orderDAO(Session session) {
        this.session = session;
    }

    public Integer save(Basket_order basket_o){
        basket_o.getOrder().addBasket_orderList(basket_o);
        basket_o.getBook().addBasket_orderList(basket_o);
        Integer id = (Integer) session.save(basket_o);
        return id;
    }

    public void delete(Basket_order basket_o) {
        session.delete(basket_o);
        basket_o.getBook().removeBasket_orderList(basket_o);
        basket_o.getOrder().removeBasket_orderList(basket_o);

    }
}
