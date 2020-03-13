package models;
import javax.persistence.*;
import java.util.List;

@Entity
@IdClass(Book_author_key.class)
@Table(name = "Books_author")

public class Book_author {
    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "id_book",
                    column = @Column(name="id_book")),
            @AttributeOverride(name = "id_customer",
                    column = @Column(name="id_author"))
    })
    private int id_book;

    private int id_author;

    @ManyToOne
    @JoinColumn(name = "id_book", referencedColumnName = "id_book", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "id_author", referencedColumnName = "id_author", nullable = false)
    private Author author;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
