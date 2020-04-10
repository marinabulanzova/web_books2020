package models;
import javax.persistence.*;

@Entity
@Table(name = "Order_basket")

public class Basket_order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "price", nullable = false)
    private Double price;

    @Column (name = "count_book", nullable = false)
    private Integer count_book;

    @ManyToOne
    @JoinColumn(name = "id_book", referencedColumnName = "id_book")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id_order", nullable = false)
    private Order order;

    public Basket_order() {
    }

    public Basket_order(Book book, Order order, Integer count_book, Double price) {
        this.book = book;
        this.order = order;
        this.count_book = count_book;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket_order basket_order = (Basket_order) o;
        if (book != null ? !book.equals(basket_order.book) : basket_order.book != null) return false;
        return order != null ? order.equals(basket_order.order) : basket_order.order == null;
    }

    @Override
    public int hashCode() {
        int result = book != null ? book.hashCode() : 0;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Basket_order{" +
                "id=" + id +
                ", price=" + price +
                ", count_book=" + count_book +
                ", book=" + book +
                ", order=" + order +
                '}';
    }
}
