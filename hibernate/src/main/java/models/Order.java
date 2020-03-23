package models;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_order;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id_user")
    private User customer;

    @Column(name = "delivery_address", nullable = true, length = 100)
    private String delivery_address;

    @Column(name = "payment_card", nullable = false)
    private Boolean payment_card;

    @Column(name = "order_date", nullable = false)
    private Timestamp order_date;

    @Column(name = "delivery_date", nullable = false)
    private Date delivery_date;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "delivery_price", nullable = false)
    private Double delivery_price;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Basket_order> basket_orderList;

    public Order() {}

    public Order(User customer, String delivery_address, Boolean payment_card, Timestamp order_date,
                 Date delivery_date, String status, Double delivery_price) {
        this.customer = customer;
        this.delivery_address = delivery_address;
        this.payment_card = payment_card;
        this.order_date = order_date;
        this.delivery_date = delivery_date;
        this.status = status;
        this.delivery_price = delivery_price;
        basket_orderList = new ArrayList();
    }

    public Integer getId_order() {
        return id_order;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public Boolean getPayment() {
        return payment_card;
    }

    public void setPayment(Boolean payment_card) {
        this.payment_card = payment_card;
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

    public Double getDelivery_price() {
        return delivery_price;
    }

    public void setDelivery_price(Double delivery_price) {
        this.delivery_price = delivery_price;
    }

    public List<Basket_order> getBasket_orderList() {
        return basket_orderList;
    }

    public void setBasket_orderList(List<Basket_order> basket_orderList) {
        this.basket_orderList = basket_orderList;
    }

    public void addBasket_orderList(Basket_order b_o) {
        b_o.setOrder(this);
        basket_orderList.add(b_o);
    }

    public void removeBasket_orderList(Basket_order b_o) {
        basket_orderList.remove(b_o);
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
        Order order = (Order) o;
        if (customer != null ? !customer.equals(order.customer) : order.customer != null) return false;
        if (delivery_address != null ? !delivery_address.equals(order.delivery_address) : order.delivery_address != null) return false;
        if (order_date!= null ? !order_date.equals(order.order_date) : order.order_date != null) return false;
        if (delivery_date != null ? !delivery_date.equals(order.delivery_date) : order.delivery_date != null) return false;
        return status != null ? status.equals(order.status) : order.status == null;
    }

    @Override
    public int hashCode() {
        int result = customer != null ? customer.hashCode() : 0;
        result = 31 * result + (delivery_address != null ? delivery_address.hashCode() : 0);
        result = 31 * result + (order_date != null ? order_date.hashCode() : 0);
        result = 31 * result + (delivery_date != null ? delivery_date.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id_order=" + id_order +
                ", customer=" + customer +
                ", delivery_address='" + delivery_address + '\'' +
                ", payment='" + payment_card + '\'' +
                ", order_date=" + order_date +
                ", delivery_date=" + delivery_date +
                ", status='" + status + '\'' +
                ", delivery_price=" + delivery_price +
                '}';
    }
}