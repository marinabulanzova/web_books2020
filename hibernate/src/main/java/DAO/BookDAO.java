package DAO;

import models.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class BookDAO {
    private SessionFactory sessionFactory;
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Book getById(int id){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Book book = (Book) session.get(Book.class, id);

        session.getTransaction().commit();
        session.close();
        return book;
    }

    public Integer save(Book book) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Integer id = (Integer) session.save(book);

        session.getTransaction().commit();
        session.close();
        return id;
    }

    public void update(Book book) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        session.update(book);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Book book) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        session.delete(book);

        session.getTransaction().commit();
        session.close();
    }

    public List<Book> findAll() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        String test_query = "SELECT b FROM Book b order by price";
        List<Book> list = (List<Book>)session.createQuery(test_query).getResultList();

        session.getTransaction().commit();
        session.close();
        return list;
    }

   // поиск книг по различным фильтрам
   public List<Book> find(String title, String genre, String publishing_house, Integer min_p_year, Integer max_p_year,
                          Integer min_p_count, Integer max_p_count, Integer count, String cover, Double min_price, Double max_price,
                          String name_author) {
       Session session = sessionFactory.openSession();
       session.getTransaction().begin();

       String text_query = "SELECT b FROM Book b";
       Boolean flagAnd = false;
       Boolean flagWhere = true;

       if (name_author != null) {
           text_query+= " JOIN b.book_authors a WHERE a.author.name = '" +  name_author + "'";
           flagAnd = true;
           flagWhere = false;
       }
       //System.out.println(text_query);
       if (title != null || genre != null || publishing_house != null || min_p_year != null || max_p_year != null ||
               min_p_count != null || max_p_count != null || count != null || cover != null || min_price != null || max_price != null ) {
           if(flagWhere) text_query += " WHERE";
           //System.out.println(text_query);

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
           //System.out.println(text_query);
           if (min_price != null) {
               text_query += (flagAnd ? " AND" : "") + " b.price >= " + min_price;
               flagAnd = true;
           }
           if (max_price != null) {
               text_query += (flagAnd ? " AND" : "") + " b.price <= " + max_price;
               flagAnd = true;
           }
       }
       text_query+=" ORDER BY price";
       System.out.println(text_query);
       List<Book> list = (List<Book>)session.createQuery(text_query).getResultList();
       session.getTransaction().commit();
       session.close();
       return list;
   }
}