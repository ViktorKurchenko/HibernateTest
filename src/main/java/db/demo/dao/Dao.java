package db.demo.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface Dao<T, ID extends Serializable> {

    T findById(ID id);

    List<T> findAll();

    T save(T entity);

    void delete(T entity);

    void flush();

    void clear();

    void setSession(Session session);

}
