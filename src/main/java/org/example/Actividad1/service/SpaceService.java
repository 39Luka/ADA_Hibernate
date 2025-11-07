package org.example.Actividad1.service;

import jakarta.persistence.PersistenceException;
import org.example.Actividad1.domain.Space;
import org.example.Actividad1.domain.Venue;
import org.example.Actividad1.dao.SpaceDao;
import org.example.Actividad1.dao.VenueDao;
import org.example.Actividad1.dao.hibernate.SpaceDaoImpl;
import org.example.Actividad1.dao.hibernate.VenueDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SpaceService {
    private final SessionFactory sf;
    private final VenueDao venueDao;
    private final SpaceDao spaceDao;

    public SpaceService(SessionFactory sf) {
        this.sf = sf;
        this.venueDao = new VenueDaoImpl();
        this.spaceDao = new SpaceDaoImpl();
    }
    public Long create(Space space){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            spaceDao.save(s,space);
            tx.commit();
            return space.getId();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
    public Space findById(Long id){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            Space space = spaceDao.findById(s,id);
            tx.commit();
            return space;
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
    public void update(Space space){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            spaceDao.update(s,space);
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
            spaceDao.deleteById(s, id);
            tx.commit();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
    public void delete(Space space){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();
            spaceDao.delete(s, space);
            tx.commit();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
    public void createService(Long venueId, Space space){
        Transaction tx = null;
        try {
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();

            Venue venue = venueDao.findById(s,venueId);
            if (venue == null) throw new PersistenceException("Venue no encontrado con ID: " + venueId);

            space.setVenue(venue);

            spaceDao.save(s, space);


            tx.commit();
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
}
