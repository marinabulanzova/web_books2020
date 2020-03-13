package models;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Authors")

public class Author {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_author", nullable = false)
    private int id_author;

    @Column (name = "name", length = 60, nullable = false)
    private String name;

    @OneToMany(mappedBy = "id_author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book_author> id_books;

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book_author> getId_books() {
        return id_books;
    }

    public void setId_books(List<Book_author> id_books) {
        this.id_books = id_books;
    }

}
