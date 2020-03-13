package models;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class Basket_customer_key implements Serializable {
    private int id_book;
    private int id_customer;

    public Basket_customer_key() {
    }

    public Basket_customer_key(int id_book, int id_customer) {
        this.id_book = id_book;
        this.id_customer = id_customer;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }
}
