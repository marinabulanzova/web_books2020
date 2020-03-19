package models;
import javax.persistence.*;

@Entity
@Table(name = "Basket_customer")

public class Basket_customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "count_book", nullable = false)
    private Integer count_book;

    @ManyToOne
    @JoinColumn(name = "id_book", referencedColumnName = "id_book", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id_user", nullable = false)
    private User customer;

    public Basket_customer() {
    }

    public Basket_customer(Book book, User customer, Integer count_book) {
        this.book = book;
        this.customer = customer;
        this.count_book = count_book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount_book() {
        return count_book;
    }

    public void setCount_book(Integer count_book) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket_customer basket_customer = (Basket_customer) o;
        if (book != null ? !book.equals(basket_customer.book) : basket_customer.book != null) return false;
        return customer != null ? customer.equals(basket_customer.customer) : basket_customer.customer == null;
    }

    @Override
    public int hashCode() {
        int result = book != null ? book.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Basket_customer{" +
                "id=" + id +
                ", count_book=" + count_book +
                ", book=" + book +
                ", customer=" + customer +
                '}';
    }
}
