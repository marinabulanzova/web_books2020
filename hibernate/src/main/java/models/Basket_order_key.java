package models;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class Basket_order_key implements Serializable {
    private int id_order;
    private int id_book;

    public Basket_order_key() {
    }

    public Basket_order_key(int id_order, int id_book) {
        this.id_order = id_order;
        this.id_book = id_book;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }
}
