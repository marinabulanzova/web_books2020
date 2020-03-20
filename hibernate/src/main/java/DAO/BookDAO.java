package DAO;

import models.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class BookDAO {
    private SessionFactory sessionFactory;

    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Book getById(int id){
        Session session = sessionFactory.openSession();
        Book book = (Book) session.get(Book.class, id);
        session.close();
        return book;
    }

    public Integer save(Book book) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();

        Integer id = (Integer) session.save(book);

        t.commit();
        session.close();
        return id;
    }

    public void update(Book book) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();

        session.update(book);

        t.commit();
        session.close();
    }

    public void delete(Book book) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();

        session.delete(book);

        t.commit();
        session.close();
    }

    public List<Book> findAll() {
        Session session = sessionFactory.openSession();
        List<Book> list = (List<Book>)session.createQuery("From Book").list();
        session.close();
        return list;
    }

   // поиск книг по различным фильтрам
   public List<Book> find(String title, String genre, String publishing_house, Integer min_p_year, Integer max_p_year,
                          Integer min_p_count, Integer max_p_count, Integer count, String cover, Double min_price, Double max_price,
                          String name_author) {
       Session session = sessionFactory.openSession();
       String text_query = "SELECT b FROM Book b";
       Boolean flagAnd = false;
       Boolean flagWhere = true;

       if (name_author != null) {
           text_query+= "JOIN b.book_authors a WHERE a.author.name = '" +  name_author + "'";
           flagAnd = true;
           flagWhere = false;
       }
       if (title != null || genre != null || publishing_house != null || min_p_year != 0 || max_p_year != 0 ||
               min_p_count != 0 || max_p_count != 0 || count != 0 || cover != null || min_price != 0 || max_price != 0 ) {
           if(flagWhere) text_query += " WHERE";
           if (title != null) {
               text_query += (flagAnd ? " AND" : "") + " b.title = '" + title + "'";
               flagAnd = true;
           }
           if (genre != null) {
               text_query += (flagAnd ? " AND" : "") + " b.genre = '" + genre + "'";
               flagAnd = true;
           }
           if (publishing_house != null) {
               text_query += (flagAnd ? " AND" : "") + " b.publishing_house = '" + publishing_house + "'";
               flagAnd = true;
           }
           if (min_p_year != null) {
               text_query += (flagAnd ? " AND" : "") + " b.publication_year   >= " + min_p_year;
               flagAnd = true;
           }
           if (max_p_year != null) {
               text_query += (flagAnd ? " AND" : "") + " b.publication_year  <= " + max_p_year;
               flagAnd = true;
           }
           if (min_p_count != null) {
               text_query += (flagAnd ? " AND" : "") + " b.page_count >= " + min_p_count;
               flagAnd = true;
           }
           if (max_p_count != null) {
               text_query += (flagAnd ? " AND" : "") + " b.page_count <= " + max_p_count;
               flagAnd = true;
           }
           if (count != null) {
               text_query += (flagAnd ? " AND" : "") + " b.count_book  = " + count;
               flagAnd = true;
           }
           if (cover != null) {
               text_query += (flagAnd ? " AND" : "") + " b.cover <= '" + cover + "'";
               flagAnd = true;
           }
           if (min_price != null) {
               text_query += (flagAnd ? " AND" : "") + " b.price >= " + min_price;
               flagAnd = true;
           }
           if (max_price != null) {
               text_query += (flagAnd ? " AND" : "") + " b.price <= " + max_price;
               flagAnd = true;
           }
       }
       return session.createQuery(text_query).getResultList();
   }
}