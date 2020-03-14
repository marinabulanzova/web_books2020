package models;
import javax.persistence.*;
import java.util.List;

/*enum cover_type {
    Твёрдая,
    Мягкая,
    Твёрдая_Тканевая,
    Кожаная
};*/

@Entity
@Table (name = "Books")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_book;

    @Column (name = "genre", length = 30, nullable = false)
    private String genre;

    @Column (name = "title", length = 50, nullable = false)
    private String title;

    @Column (name = "publishing_house", length = 30, nullable = false)
    private String publishing_house;

    @Column (name = "publication_year", nullable = false)
    private int publication_year;

    @Column (name = "page_count", nullable = false)
    private int page_count;

    @Column (name = "count_book", nullable = false)
    private int count_book;

    @Column (name = "cover", nullable = false)
    private /*cover_type*/String cover;

    @Column (name = "price", nullable = false)
    private double price;

    @OneToMany(mappedBy = "id_book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <Book_author> id_authors;

    @OneToMany(mappedBy = "id_book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <Basket_customer> id_customers_in_basket;

    @OneToMany(mappedBy = "id_book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <Basket_order> id_orders;

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishing_house() {
        return publishing_house;
    }

    public void setPublishing_house(String publishing_house) {
        this.publishing_house = publishing_house;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public int getCount_book() {
        return count_book;
    }

    public void setCount_book(int count_book) {
        this.count_book = count_book;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}