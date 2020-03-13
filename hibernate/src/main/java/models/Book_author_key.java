package models;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class Book_author_key implements Serializable {
    private int id_book;
    private int id_author;

    public Book_author_key() {
    }

    public Book_author_key(int id_book, int id_author) {
        this.id_book = id_book;
        this.id_author = id_author;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }
}
