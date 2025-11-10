package org.example.Actividad1.service;

import jakarta.persistence.PersistenceException;
import org.example.Actividad1.dao.BookingDao;
import org.example.Actividad1.dao.SpaceDao;
import org.example.Actividad1.dao.UserDao;
import org.example.Actividad1.dao.hibernate.BookingDaoImpl;
import org.example.Actividad1.dao.hibernate.SpaceDaoImpl;
import org.example.Actividad1.dao.hibernate.UserDaoImpl;
import org.example.Actividad1.domain.*;
import org.example.Actividad1.dto.VenueIncomeDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;

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
    public Long create(Booking booking){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            bookingDao.save(s,booking);
            tx.commit();
            return booking.getId();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
    public Booking findById(Long id){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            Booking booking = bookingDao.findById(s,id);
            tx.commit();
            return booking;
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
    public void update(Booking booking){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            bookingDao.update(s,booking);
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
            bookingDao.deleteById(s, id);
            tx.commit();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
    public void delete(Booking booking){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            bookingDao.delete(s, booking);
            tx.commit();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
    public Long create(Long userId, Long spaceId, LocalDateTime start, LocalDateTime end, Integer totalPrice, BookingStatus status){
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
            booking.setCreatedAt(LocalDateTime.now());

            Long bookingId =bookingDao.save(s, booking);

            tx.commit();
            return bookingId;
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }


    }

    public List<VenueIncomeDto> venueEnRango(LocalDateTime start, LocalDateTime end){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();

            List<VenueIncomeDto> venues = bookingDao.venuesConIngresosEntreFechas(s,start,end);

            tx.commit();
            return venues;
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    public List<Booking> bookingsConfirmedByRange(LocalDateTime start, LocalDateTime end){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();

            List<Booking> bookings = bookingDao.bookingsConfirmedByRange(s,start,end);

            tx.commit();
            return bookings;
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }

    }
    public List<Space> spacesConfirmedByIncome(){
        Transaction tx = null;
        try {
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();

            List<Space> spaces = bookingDao.top3SpaceConfirmedIncomes(s);

            tx.commit();
            return spaces;
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }

}
