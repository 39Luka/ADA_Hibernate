package org.example.dao.hibernate;

import org.example.Actividad1.Venue;
import org.example.dao.VenueDao;

public class VenueDaoImpl extends GenericDaoImpl<Venue,Long> implements VenueDao {

    public VenueDaoImpl(Class<Venue> entityClass) {
        super(entityClass);
    }
}
