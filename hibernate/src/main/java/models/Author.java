package models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Authors")

public class Author {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_author", nullable = false)
    private Integer id_author;

    @Column (name = "name", length = 60, nullable = false)
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Book_author> book_authors;


    public Author() {}

    public Author(String name) {
        this.name = name;
        book_authors = new ArrayList();
    }

    public List<Book_author> getBook_authors() {
        return book_authors;
    }

    public void setBook_authors(List<Book_author> book_authors) {
        this.book_authors = book_authors;
    }

    public void addBook_authors(Book_author book_author) {
        book_author.setAuthor(this);
        book_authors.add(book_author);
    }

    public void removeBook_authors(Book_author book_author) {
        book_authors.remove(book_author);
    }

    public Integer getId_author() {
        return id_author;
    }

    public void setId_author(Integer id_author) {
        this.id_author = id_author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return name.equals(author.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Author{" +
                "id_author=" + id_author +
                ", name='" + name + '\'' +
                '}';
    }
}
