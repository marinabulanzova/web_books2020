package models;
import javax.persistence.*;

@Entity
@IdClass(Basket_order_key.class)
@Table(name = "Baskets_customer")

public class Basket_order {
    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "id_order",
                    column = @Column(name="id_order")),
            @AttributeOverride(name = "id_book",
                    column = @Column(name="id_book"))
    })
    private int id_order;

    private int id_book;

    @Column (name = "price", nullable = false)
    private double price;

    @Column (name = "count_book", nullable = false)
    private int count_book;

    @ManyToOne
    @JoinColumn(name = "id_book", referencedColumnName = "id_book", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id_order", nullable = false)
    private Order order;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
