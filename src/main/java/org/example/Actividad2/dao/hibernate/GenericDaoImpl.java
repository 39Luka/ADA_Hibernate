package org.example.Actividad2.dao.hibernate;

import org.example.Actividad1.dao.GenericDao;
import org.hibernate.Session;

public class GenericDaoImpl<T, ID> implements GenericDao<T, ID> {

    private final Class<T> entityClass;
    private final Class<ID> idClass;

    public GenericDaoImpl(Class<T> entityClass, Class<ID> idClass) {
        this.entityClass = entityClass;
        this.idClass = idClass;
    }

    @Override
    public ID save(Session session, T entity) {
        session.persist(entity);
        session.flush();
        return idClass.cast(session.getIdentifier(entity)); // cast seguro
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
        T entity = session.find(entityClass, id);
        if (entity != null) {
            session.remove(entity);
            return true;
        }
        return false;
    }
}
