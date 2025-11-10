package org.example.Actividad1.dao;

import org.example.Actividad1.domain.Venue;
import org.hibernate.Session;

import java.util.List;

public interface VenueDao extends GenericDao<Venue, Long> {
     List<Venue> venuePorCiudad(Session session, String city);

}
