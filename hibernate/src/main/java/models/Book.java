package models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_book;

    @Column (name = "genre", length = 30, nullable = false)
    private String genre;

    @Column (name = "title", length = 50, nullable = false)
    private String title;

    @Column (name = "publishing_house", length = 30, nullable = false)
    private String publishing_house;

    @Column (name = "publication_year", nullable = false)
    private Integer publication_year;

    @Column (name = "page_count", nullable = false)
    private Integer page_count;

    @Column (name = "count_book", nullable = false)
    private Integer count_book;

    @Column (name = "cover", nullable = false)
    private String cover;

    @Column (name = "price", nullable = false)
    private Double price;

    //cascade={CascadeType.REFRESH, CascadeType.MERGE}, orphanRemoval = false
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <Book_author> book_authors;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <Basket_customer> basket_customerList;

    @OneToMany(mappedBy = "book", cascade={CascadeType.REFRESH, CascadeType.MERGE}, orphanRemoval = false)
    private List <Basket_order> basket_orderList;

    public Book() {}

    public Book(String genre, String title, String publishing_house, Integer publication_year,
                Integer page_count, Integer count_book, String cover, Double prise) {
        this.genre = genre;
        this.title = title;
        this.publication_year = publication_year;
        this.publishing_house = publishing_house;
        this.page_count = page_count;
        this.count_book = count_book;
        this.cover = cover;
        this.price = prise;
        basket_orderList = new ArrayList();
        basket_customerList = new ArrayList();
        book_authors = new ArrayList();
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

    public Integer getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(Integer publication_year) {
        this.publication_year = publication_year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId_book() {
        return id_book;
    }

    public void setId_book(Integer id_book) {
        this.id_book = id_book;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPage_count() {
        return page_count;
    }

    public void setPage_count(Integer page_count) {
        this.page_count = page_count;
    }

    public Integer getCount_book() {
        return count_book;
    }

    public void setCount_book(Integer count_book) {
        this.count_book = count_book;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<Book_author> getBook_authors() {
        return book_authors;
    }

    public void setBook_authors(List<Book_author> book_authors) {
        this.book_authors = book_authors;
    }

    public void addBook_authors(Book_author book_author) {
        book_author.setBook(this);
        book_authors.add(book_author);
    }

    public void removeBook_authors(Book_author book_author) {
        book_authors.remove(book_author);
    }

    public List<Basket_customer> getBasket_customerList() {
        return basket_customerList;
    }

    public void setBasket_customerList(List<Basket_customer> basket_customerList) {
        this.basket_customerList = basket_customerList;
    }

    public void addBasket_customerList(Basket_customer b_c) {
        b_c.setBook(this);
        basket_customerList.add(b_c);
    }

    public void removeBasket_customerList(Basket_customer b_c) {
        basket_customerList.remove(b_c);
        b_c.setBook(null);
    }

    public void updateBasket_customerList(Basket_customer b_c) {
        basket_customerList.remove(b_c);
        b_c.setBook(null);
    }

    public List<Basket_order> getBasket_orderList() {
        return basket_orderList;
    }

    public void setBasket_orderList(List<Basket_order> basket_orderList) {
        this.basket_orderList = basket_orderList;
    }

    public void addBasket_orderList(Basket_order b_o) {
        b_o.setBook(this);
        basket_orderList.add(b_o);
    }

    public void removeBasket_orderList(Basket_order b_o) {
        basket_orderList.remove(b_o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        //if (id_book != null ? !id_book.equals(book.id_book) : book.id_book != null) return false;
        if (genre != null ? !genre.equals(book.genre) : book.genre != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (publishing_house!= null ? !publishing_house.equals(book.publishing_house) : book.publishing_house != null) return false;
        if (publication_year != null ? !publication_year.equals(book.publication_year) : book.publication_year != null) return false;
        if (page_count != null ? !page_count.equals(book.page_count) : book.page_count != null) return false;
        if (cover != null ? !cover.equals(book.cover) : book.cover != null) return false;
        return price != null ? price.equals(book.price) : book.price == null;
    }

    @Override
    public int hashCode() {
        //int result = id_book != null ? id_book.hashCode() : 0;
        //result = 31 * result + (genre != null ? genre.hashCode() : 0);
        int result = genre != null ? genre.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (publishing_house != null ? publishing_house.hashCode() : 0);
        result = 31 * result + (publication_year != null ? publication_year.hashCode() : 0);
        result = 31 * result + (page_count != null ? page_count.hashCode() : 0);
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id_book=" + id_book +
                ", genre='" + genre + '\'' +
                ", title='" + title + '\'' +
                ", publishing_house='" + publishing_house + '\'' +
                ", publication_year=" + publication_year +
                ", page_count=" + page_count +
                ", count_book=" + count_book +
                ", cover='" + cover + '\'' +
                ", price=" + price +
                '}';
    }
}