package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")

public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_user;

    @Column (name = "surname", nullable = true, length = 20)
    private String surname;

    @Column (name = "first_name", nullable = false, length = 20)
    private String first_name;

    @Column (name = "patronymic", nullable = true, length = 20)
    private String patronymic;

    @Column (name = "address", nullable = true, length = 100)
    private String address;

    @Column (name = "phone_number", nullable = false, unique = true, length = 11)
    private String phone_number;

    @Column (name = "e_mail", unique = true, nullable = false, length = 30)
    private String e_mail;

    @Column (name = "password_hash", nullable = false, length = 50)
    private String password_hash;

    @Column (name = "admin")
    private Boolean admin;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Basket_customer> basket_customerList;

    @OneToMany(mappedBy = "customer", cascade={CascadeType.REFRESH, CascadeType.MERGE}, orphanRemoval = false)
    private List<Order> orders;

    public User() {}

    public User(String surname, String first_name, String patronymic, String address,
                String phone_number, String e_mail, String password_hash, Boolean admin) {
        this.surname = surname;
        this.first_name = first_name;
        this.patronymic = patronymic;
        this.address = address;
        this.phone_number = phone_number;
        this.e_mail = e_mail;
        this.password_hash = password_hash;
        this.admin = admin;
        basket_customerList = new ArrayList();
        orders = new ArrayList();
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public List<Basket_customer> getBasket_customerList() {
        return basket_customerList;
    }

    public void setBasket_customerList(List<Basket_customer> basket_customerList) {
        this.basket_customerList = basket_customerList;
    }

    public void addBasket_customerList(Basket_customer b_c) {
        b_c.setCustomer(this);
        basket_customerList.add(b_c);
    }

    public void removeBasket_customerList(Basket_customer b_c) {
        basket_customerList.remove(b_c);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        order.setCustomer(this);
        orders.add( order );
    }

    public void removeOrder(Order order) {
        orders.remove( order );
        order.setCustomer(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return phone_number.equals(user.phone_number);
    }

    @Override
    public int hashCode() {
        return phone_number.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", surname='" + surname + '\'' +
                ", first_name='" + first_name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", e_mail='" + e_mail + '\'' +
                ", password_hash='" + password_hash + '\'' +
                ", admin=" + admin +
                '}';
    }
}
