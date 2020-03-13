package models;
import javax.persistence.*;

@Entity
@IdClass(Basket_customer_key.class)
@Table(name = "Baskets_customer")

public class Basket_customer {
    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "id_book",
                    column = @Column(name="id_book")),
            @AttributeOverride(name = "id_customer",
                    column = @Column(name="id_customer"))
    })
    private int id_book;

    private int id_customer;

    @Column (name = "count_book", nullable = false)
    private int count_book;

    @ManyToOne
    @JoinColumn(name = "id_book", referencedColumnName = "id_book", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id_user", nullable = false)
    private User customer;

    public int getCount_book() {
        return count_book;
    }

    public void setCount_book(int count_book) {
        this.count_book = count_book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }
}
