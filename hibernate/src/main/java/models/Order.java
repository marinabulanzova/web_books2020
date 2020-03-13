package models;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_order;

    @Column(name = "id_customer")
    private int id_customer;

    @Column(name = "delivery_address", nullable = true, length = 100)
    private String delivery_address;

    @Column(name = "payment", nullable = false)
    private String /*payment_method*/ payment;

    @Column(name = "order_date", nullable = false)
    private Timestamp order_date;

    @Column(name = "delivery_date", nullable = false)
    private Date delivery_date;

    @Column(name = "status", nullable = false)
    private String /*order_status*/ status;

    @Column(name = "delivery_price", nullable = false)
    private double delivery_price;

    @OneToMany(mappedBy = "id_order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Basket_order> id_books_in_basket_order;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id_user", nullable = false)
    private User customer;

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Timestamp getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Timestamp order_date) {
        this.order_date = order_date;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getDelivery_price() {
        return delivery_price;
    }

    public void setDelivery_price(double delivery_price) {
        this.delivery_price = delivery_price;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }
}
