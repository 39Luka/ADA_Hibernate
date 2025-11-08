package org.example.Actividad2.dao.hibernate;

import org.example.Actividad1.dao.GenericDao;
import org.hibernate.Session;

public class GenericDaoImpl<T, ID> implements GenericDao<T, ID> {
    private Class<T> entityClass;

    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public ID save(Session session, T entity) {
        session.persist(entity);
        session.flush();
        return (ID) session.getIdentifier(entity);
    }

    @Override
    public T findById(Session session, ID id) {
        return session.find(entityClass, id);
    }

    @Override
    public T update(Session session, T entity) {
        return session.merge(entity);
    }

    @Override
    public void delete(Session session, T entity) {
        session.remove(entity);
    }

    @Override
    public boolean deleteById(Session session, ID id) {
        T entity = session.find(entityClass,id);
        if (entity != null){
            session.remove(entity);
            return true;
        }
        return false;
    }
}
