package org.example.Actividad1;

import org.example.Actividad1.domain.HibernateUtil1;
import org.example.Actividad1.domain.Space;
import org.example.Actividad1.domain.Venue;
import org.example.Actividad1.service.SpaceService;
import org.example.Actividad1.service.VenueService;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil1.getSessionFactory();

        VenueService venueService = new VenueService(sf);
        Venue venue = new Venue();
        venue.setAddress("Calle Nueva");
        venue.setCity("Mayorca");
        venue.setName("La gran sede");
        Long venueId = venueService.create(venue);

        SpaceService spaceService = new SpaceService(sf);
        Space space = new Space();
        space.setName("El espacio");

    }
}
