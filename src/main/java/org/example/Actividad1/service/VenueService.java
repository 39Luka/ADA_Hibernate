package org.example.Actividad1.service;

import org.hibernate.SessionFactory;

public class VenueService {
    private final SessionFactory sf;

    public VenueService(SessionFactory sf) {
        this.sf = sf;
    }


}
