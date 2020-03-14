package DAO;

import models.Book;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import javax.persistence.TypedQuery;

public class BookDAO {
    private SessionFactory sessionFactory;

    public Book getById(int id){
        Session session = sessionFactory.openSession();
        Book book = (Book) session.get(Book.class, id);
        session.close();
        return book;
    }

    public void save(Book book){
        Session session = sessionFactory.openSession();
        Transaction t1 = session.beginTransaction();
        session.save(book);
        t1.commit();
        session.close();
    }

    public void update(Book book) {
        Session session = sessionFactory.openSession();
        Transaction t2 = session.beginTransaction();
        session.update(book);
        t2.commit();
        session.close();
    }

    public void delete(Book book) {
        Session session = sessionFactory.openSession();
        Transaction t3 = session.beginTransaction();
        session.delete(book);
        t3.commit();
        session.close();
    }

    public List<Book> findAll() {
        Session session = sessionFactory.openSession();
        List<Book> list = (List<Book>)session.createQuery("From Book").list();
        session.close();
        return list;
    }

   /* public List<Book> find_by_title(String title) {
        Session session = sessionFactory.openSession();
        String query = "SELECT b from Book b where b.title = :title";
        TypedQuery<Book> q = session.createQuery(query, Book.class);
            q.setParameter("title",title );
        return q.getResultList();
    }

    public List<Book> find_by_price(double min, double max) {
        Session session = sessionFactory.openSession();
        String query = "SELECT b from Book b where b.price between :min and :max order by b.price";
        TypedQuery<Book> q = session.createQuery(query, Book.class);
        q.setParameter("max", max);
        q.setParameter("min", min);
        return q.getResultList();
    }

    public List<Book> find_by_genre(String genre) {
        Session session = sessionFactory.openSession();
        String query = "SELECT b from Book b where b.genre = :genre";
        TypedQuery<Book> q = session.createQuery(query, Book.class);
        q.setParameter("genre", genre);
        return q.getResultList();
    }

    public List<Book> find_by_publishing_house(String p_h) {
        Session session = sessionFactory.openSession();
        String query = "SELECT b from Book b where b.publishing_house = :p_h";
        TypedQuery<Book> q = session.createQuery(query, Book.class);
        q.setParameter("p_h", p_h );
        return q.getResultList();
    }

    public List<Book> find_by_publication_year (int min, int max) {
        Session session = sessionFactory.openSession();
        String query = "SELECT b from Book b where publication_year between :min and :max";
        TypedQuery<Book> q = session.createQuery(query, Book.class);
        q.setParameter("max", max);
        q.setParameter("min", min);
        return q.getResultList();
    }


    public int count_books(int bookId) {
        Session session = sessionFactory.openSession();
        String query = "SELECT b.count_book from Book b where b.id_book = :id_book";
        TypedQuery<Book> q = session.createQuery(query, Book.class);
        return (int) q.getResultList();
    }*/

   // поиск книг по различным фильтрам
   public List<User> find(String title, String genre, String publishing_house, int min_p_year, int max_p_year,
                          int min_p_count, int max_p_count, int count, String cover, double min_price, double max_price) {
       Session session = sessionFactory.openSession();
       String text_query = "SELECT b FROM Book b";
       if (title != null ||
               genre != null ||
               publishing_house != null ||
               min_p_year != 0 ||
               max_p_year != 0 ||
               min_p_count != 0 ||
               max_p_count != 0 ||
               count != 0 ||
               cover != null ||
               min_price != 0 || 
               max_price != 0
       ) text_query += " WHERE";
       Boolean needAnd = false;
       if (title != null) {
           text_query += (needAnd ? " AND":"")+" u.title = '" + title + "'"; needAnd = true;
       }
       if (genre != null) {
           text_query += (needAnd ? " AND":"") + " u.genre = '" + genre  + "'"; needAnd = true;
       }
       if (publishing_house != null) {
           text_query += (needAnd ? " AND":"") + " u.publishing_house = '" + publishing_house + "'"; needAnd = true;
       }
       if (min_p_year != 0) {
           text_query += (needAnd ? " AND":"") + " u.publication_year   >= '" + min_p_year    + "'"; needAnd = true;
       }
       if (max_p_year != 0) {
           text_query += (needAnd ? " AND":"") + " u.publication_year  <= '" + max_p_year  + "'"; needAnd = true;
       }
       if (min_p_count != 0) {
           text_query += (needAnd ? " AND":"") + " c.page_count >= '" + min_p_count + "'"; needAnd = true;
       }
       if (max_p_count != 0) {
           text_query += (needAnd ? " AND":"") +" c.page_count <= '" + max_p_count + "'"; needAnd = true;
       }
       if (count != 0) {
           text_query += (needAnd ? " AND":"") +" c.count_book  ='" + count + "'"; needAnd = true;
       }
       if (cover != null) {
           text_query += (needAnd ? " AND":"") +" c.cover <= '" + cover + "'"; needAnd = true;
       }
       if (min_price != 0) {
           text_query += (needAnd ? " AND":"") + " c.price >= '" + min_price + "'"; needAnd = true;
       }
       if (max_price != 0) {
           text_query += (needAnd ? " AND":"") +" c.price <= '" + max_price + "'"; needAnd = true;
       }
       return session.createQuery(text_query).getResultList();
   }
}
