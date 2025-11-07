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
