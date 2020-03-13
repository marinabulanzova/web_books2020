package models;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users")

public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id_user;

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

    @Column (name = "status")
    private String /*status_user*/ status;

    @OneToMany(mappedBy = "id_user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Basket_customer> id_books_in_basket;

    @OneToMany(mappedBy = "id_user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
