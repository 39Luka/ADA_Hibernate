package org.example.Actividad1.dao.hibernate;

import org.example.Actividad1.domain.Venue;
import org.example.Actividad1.dao.VenueDao;

public class VenueDaoImpl extends GenericDaoImpl<Venue,Long> implements VenueDao {

    public VenueDaoImpl() {
        super(Venue.class);
    }
}
