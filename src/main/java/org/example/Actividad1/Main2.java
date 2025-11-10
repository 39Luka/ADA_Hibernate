package org.example.Actividad1;

import org.example.Actividad1.domain.HibernateUtil1;
import org.example.Actividad1.service.BookingService;
import org.example.Actividad1.service.SpaceService;
import org.example.Actividad1.service.VenueService;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;

public class Main2 {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil1.getSessionFactory();
       // SpaceService spaceService = new SpaceService(sf);
        //System.out.println(spaceService.getEspaciosNoReservados());
        //System.out.println(spaceService.getMostPorfitSpace());

        //Actividad 1.1, Venues por ciudad
        VenueService venueService = new VenueService(sf);
        System.out.println("Actividad 1.1, Venues por ciudad");
        System.out.println(venueService.getVenueByCiudad("Madrid"));
        //Actividad 1.2, Top 5 ciudades con más espacios
        SpaceService spaceService = new SpaceService(sf);
        System.out.println("Actividad 1.2, Top 5 ciudades con más espacios");
        System.out.println(spaceService.top5CiudadesMasEspacios());
        //Actividad 1.3, Venues con ingresos confirmados en rango de fechas
        System.out.println("Actividad 1.3, Venues con ingresos confirmados en rango de fechas");
        BookingService bookingService = new BookingService(sf);
        bookingService.venueEnRango(LocalDateTime.of(2025,5,15,5,5),LocalDateTime.of(2025,1,10,5,5));


    }


}
