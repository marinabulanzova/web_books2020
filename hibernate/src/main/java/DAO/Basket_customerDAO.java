package DAO;

import models.Basket_customer;
import models.Book;
import models.Book_author;
import org.hibernate.Session;
import sun.swing.BakedArrayList;

import java.util.List;


public class Basket_customerDAO {
    private Session session;

    public Basket_customerDAO(Session session) {
        this.session = session;
    }

    public Basket_customer getById(int id){
        Basket_customer b_c = (Basket_customer) session.get(Basket_customer.class, id);
        return b_c;
    }
    // добавить в корзину
    public Integer save(Basket_customer basket_c){
        basket_c.getCustomer().addBasket_customerList(basket_c);
        basket_c.getBook().addBasket_customerList(basket_c);
        Integer id = (Integer) session.save(basket_c);
        return id;
    }

    // убрать из корзины
    public void delete(Basket_customer basket_c) {
        session.delete(basket_c);
        basket_c.getBook().removeBasket_customerList(basket_c);
        basket_c.getCustomer().removeBasket_customerList(basket_c);
    }

    public List<Basket_customer> find(Integer id_user) {
        String test_query = "SELECT b FROM Basket_customer b WHERE b.customer.id = " + id_user;
        List<Basket_customer> list = (List<Basket_customer>)session.createQuery(test_query).getResultList();
        return list;
    }
}
