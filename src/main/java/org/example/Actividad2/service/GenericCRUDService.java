package org.example.Actividad2.service;

import org.example.Actividad2.dao.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GenericCRUDService<T, ID> {

    private final Class<T> clase;
    private final SessionFactory sf;
    private final GenericDao<T, ID> genericDao;

    public GenericCRUDService(SessionFactory sf, Class<T> clase, GenericDao<T, ID> genericDao) {
        this.sf = sf;
        this.clase = clase;
        this.genericDao = genericDao;
    }

    public ID create(T entity) {
        Transaction tx = null;
        try {
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            ID id = genericDao.save(s, entity);
            tx.commit();
            return id;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public T findById(ID id) {
        Transaction tx = null;
        try {
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            T entity = genericDao.findById(s, id);
            tx.commit();
            return entity;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public T update(T entity) {
        Transaction tx = null;
        try {
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            T updated = genericDao.update(s, entity);
            tx.commit();
            return updated;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public boolean delete(T entity) {
        Transaction tx = null;
        try {
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            genericDao.delete(s, entity);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        }
    }

    public boolean deleteById(ID id) {
        Transaction tx = null;
        try {
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            boolean deleted = genericDao.deleteById(s, id);
            tx.commit();
            return deleted;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
