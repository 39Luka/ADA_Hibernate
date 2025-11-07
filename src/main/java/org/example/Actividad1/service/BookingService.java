package org.example.Actividad1.service;

import jakarta.persistence.PersistenceException;
import org.example.Actividad1.dao.BookingDao;
import org.example.Actividad1.dao.SpaceDao;
import org.example.Actividad1.dao.UserDao;
import org.example.Actividad1.dao.hibernate.BookingDaoImpl;
import org.example.Actividad1.dao.hibernate.SpaceDaoImpl;
import org.example.Actividad1.dao.hibernate.UserDaoImpl;
import org.example.Actividad1.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class BookingService {
    private final SessionFactory sf;
    private final UserDao userDao;
    private final SpaceDao spaceDao;
    private final BookingDao bookingDao;

    public BookingService(SessionFactory sf) {
        this.sf = sf;
        this.userDao = new UserDaoImpl();
        this.spaceDao = new SpaceDaoImpl();
        this.bookingDao = new BookingDaoImpl();
    }

    public void create(Long userId, Long spaceId, LocalDateTime start, LocalDateTime end, Integer totalPrice, BookingStatus status){
        Transaction tx = null;
        try {
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();

            User user = userDao.findById(s,userId);
            if (user == null) throw new PersistenceException("Usuario no encontrado con ID: " + userId);

            Space space = spaceDao.findById(s,spaceId);
            if (space == null) throw new PersistenceException("Space no encontrado con ID: " + spaceId);

            Booking booking =  new Booking();
            booking.setStartTime(start);
            booking.setEndTime(end);
            booking.setTotalPrice(totalPrice);
            booking.setStatus(status);
            booking.setUser(user);
            booking.setSpace(space);

            bookingDao.save(s, booking);

            tx.commit();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }


    }
}
