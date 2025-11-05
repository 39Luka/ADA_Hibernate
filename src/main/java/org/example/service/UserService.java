package org.example.service;

import jakarta.persistence.PersistenceException;
import org.example.Actividad1.User;
import org.example.dao.UserDao;
import org.example.dao.hibernate.UserDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class UserService {
    private final SessionFactory sf;
    private final UserDao userDao;

    public UserService(SessionFactory sf) {
        this.sf = sf;
        this.userDao = new UserDaoImpl();
    }
    public Long create(User user){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            userDao.save(s,user);
            tx.commit();
            return user.getId();
        }catch (PersistenceException e){
           if(tx != null) tx.rollback();
           throw e;
        }
    }

    public User findById(Long id){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            User user = userDao.findById(s,id);
            tx.commit();
            return user;
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    public void update(User user){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            userDao.update(s,user);
            tx.commit();
        }catch (PersistenceException e){
            if(tx != null ) tx.rollback();
            throw e;
        }
    }
    public void deleteById(Long id){
        Transaction tx = null;
        try {
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            userDao.deleteById(s, id);
            tx.commit();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    public void delete(User user){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            userDao.delete(s, user);
            tx.commit();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }


    public void assignAccessCard(Long userId, String uid){

    }
}
