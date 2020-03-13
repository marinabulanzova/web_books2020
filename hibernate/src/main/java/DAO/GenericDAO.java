package DAO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface GenericDAO <T extends Serializable> {
    void add(T newObject) throws SQLException;
    void delete(T object) throws SQLException;
    List<T> findAll() throws SQLException;
    T findById(long id) throws SQLException;


}
