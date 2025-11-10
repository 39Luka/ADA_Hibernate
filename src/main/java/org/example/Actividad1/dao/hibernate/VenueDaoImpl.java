package org.example.Actividad1.dao.hibernate;

import org.example.Actividad1.domain.Venue;
import org.example.Actividad1.dao.VenueDao;
import org.hibernate.Session;

import java.util.List;

public class VenueDaoImpl extends GenericDaoImpl<Venue,Long> implements VenueDao {

    public VenueDaoImpl() {
        super(Venue.class);
    }

    @Override
    public List<Venue> venuePorCiudad(Session session, String city) {
        return session.createNamedQuery("Venue.findByName", Venue.class)
                .setParameter("city", city)
                .getResultList();
    }

}
