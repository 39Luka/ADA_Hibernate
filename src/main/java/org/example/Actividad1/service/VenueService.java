package org.example.Actividad1.service;

import org.example.Actividad1.dao.VenueDao;
import org.example.Actividad1.dao.hibernate.VenueDaoImpl;
import org.example.Actividad1.domain.Venue;
import org.example.Actividad1.dto.MostProfitSpacesDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import jakarta.persistence.PersistenceException;

import java.util.List;

public class VenueService {
    private final SessionFactory sf;
    private final VenueDao venueDao ;

    public VenueService(SessionFactory sf) {
        this.sf = sf;
        this.venueDao = new VenueDaoImpl();
    }



    public Long create(Venue venue){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            venueDao.save(s,venue);
            tx.commit();
            return venue.getId();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
    public Venue findById(Long id){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            Venue venue = venueDao.findById(s,id);
            tx.commit();
            return venue;
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
    public void update(Venue venue){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            venueDao.update(s,venue);
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
            venueDao.deleteById(s, id);
            tx.commit();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
    public void delete(Venue venue){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            venueDao.delete(s, venue);
            tx.commit();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    public List<Venue> getVenueByCiudad(String city){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();

            List<Venue> venues = venueDao.venuePorCiudad(s,city);

            tx.commit();
            return venues;
        }catch (PersistenceException e){
            if (tx != null) tx.rollback();
            throw e;
        }
    }


}
