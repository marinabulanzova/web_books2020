package models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Books_authors")

public class Book_author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_book", referencedColumnName = "id_book", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "id_author", referencedColumnName = "id_author", nullable = false)
    private Author author;

    public Book_author() {
    }

    public Book_author(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book_author book_author = (Book_author) o;
        if (book != null ? !book.equals(book_author.book) : book_author.book != null) return false;
        return author != null ? author.equals(book_author.author) : book_author.author == null;
    }

    @Override
    public int hashCode() {
        int result = book != null ? book.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book_author{" +
                "id=" + id +
                ", book=" + book +
                ", author=" + author +
                '}';
    }
}
