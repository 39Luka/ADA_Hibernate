package org.example.Actividad1.service;

import jakarta.persistence.PersistenceException;
import org.example.Actividad1.domain.AccessCard;
import org.example.Actividad1.domain.User;
import org.example.Actividad1.dao.AccessCardDao;
import org.example.Actividad1.dao.UserDao;
import org.example.Actividad1.dao.hibernate.AccessCardDaoImpl;
import org.example.Actividad1.dao.hibernate.UserDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserService {
    private final SessionFactory sf;
    private final UserDao userDao;
    private final AccessCardDao accessCardDao;

    public UserService(SessionFactory sf) {
        this.sf = sf;
        this.accessCardDao = new AccessCardDaoImpl();
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
        Transaction tx = null;
        try {
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();

            AccessCard newAccessCard = new AccessCard();
            newAccessCard.setCardUid(uid);
            newAccessCard.setActive(true);
            accessCardDao.save(s, newAccessCard);

            User user = userDao.findById(s, userId);
            if (user == null) throw new PersistenceException("Usuario no encontrado");
            user.setAccessCard(newAccessCard);
            userDao.update(s, user);

            tx.commit();
        }catch (PersistenceException e){
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void updateAccessCard(Long userId, boolean active, String newUid){
        Transaction tx = null;
        try {
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();

            User user = userDao.findById(s,userId);
            if (user == null) throw new PersistenceException("Usuario no encontrado");

            AccessCard accessCard = user.getAccessCard();
            if (accessCard == null) throw new PersistenceException("El usuario no tiene tarjeta asignada");

            accessCard.setCardUid(newUid);
            accessCard.setActive(active);

            accessCardDao.update(s, accessCard);

            tx.commit();
        }catch (PersistenceException e){
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void removeAccessCard(Long userId){
        Transaction tx = null;
        try {
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();

            User user = userDao.findById(s,userId);
            if (user == null) throw new PersistenceException("Usuario no encontrado");

            AccessCard accessCard = user.getAccessCard();
            if (accessCard == null) throw new PersistenceException("El usuario no tiene tarjeta asignada");

            user.setAccessCard(null);
            userDao.update(s,user);

            accessCardDao.delete(s, accessCard);

            tx.commit();
        }catch (PersistenceException e){
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
