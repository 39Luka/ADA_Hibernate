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
