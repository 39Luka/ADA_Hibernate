package org.example.Actividad2.dao;

import org.hibernate.Session;

public interface GenericDao<T, ID> {

    ID save(Session session, T entity);
    T findById(Session session, ID id);
    T update(Session session, T entity);
    void delete(Session session, T entity);
    boolean deleteById(Session session, ID id);
}
