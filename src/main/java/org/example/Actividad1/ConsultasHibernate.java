package org.example.Actividad1;

import org.example.Actividad1.domain.Booking;
import org.example.Actividad1.domain.HibernateUtil1;
import org.example.Actividad1.domain.Space;
import org.example.Actividad1.domain.Tag;
import org.example.Actividad1.dto.SpaceByVenueTagDto;
import org.example.Actividad1.dto.TagUseDto;
import org.example.Actividad1.dto.VenueIncomeDto;
import org.example.Actividad1.service.BookingService;
import org.example.Actividad1.service.SpaceService;
import org.example.Actividad1.service.TagService;
import org.example.Actividad1.service.VenueService;
import org.hibernate.SessionFactory;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ConsultasHibernate {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil1.getSessionFactory();
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
        List<VenueIncomeDto> lista = bookingService.venueEnRango(LocalDateTime.of(2025,11,12,5,5),LocalDateTime.of(2025,11,13,5,5));
        System.out.println(lista);
        //Actividad 1.4, Espacios activos por capacidad mínima y precio máximo
        System.out.println("Actividad 1.4, Espacios activos por capacidad mínima y precio máximo");
        List<Space> spacesL = spaceService.activeCapacidadMinPrecioMax(15, BigDecimal.valueOf(8));
        System.out.println(spacesL);
        //Actividad 1.5 Espacios nunca reservados
        System.out.println("Actividad 1.5 Espacios nunca reservados");
        System.out.println(spaceService.getEspaciosNoReservados());
        //Actividad 1.6 Reservas confirmadas por venue y rango
        System.out.println("Actividad 1.6 Reservas confirmadas por venue y rango");
        List<Booking> bookings = bookingService.bookingsConfirmedByRange(LocalDateTime.of(2025,11,12,5,5),LocalDateTime.of(2025,11,13,5,5));
        System.out.println(bookings);
        //Actividad 1.7 Top 3 espacios por ingresos confirmados
        System.out.println("Actividad 1.7 Top 3 espacios por ingresos confirmados");
        List<Space> spacesList = bookingService.spacesConfirmedByIncome();
        System.out.println(spacesList);
        //Actividad 1.8 Tags más usados
        System.out.println("Actividad 1.8 Tags más usados");
        TagService tagService = new TagService(sf);
        List<TagUseDto> tagsUse = tagService.usoTags();
        System.out.println(tagsUse);
        //Actividad 1.9 Devuelve los Tag cuyo nombre empiece por un texto dado, ignorando mayúsculas/minúsculas.
        System.out.println("Actividad 1.9 Devuelve los Tag cuyo nombre empiece por un texto dado, ignorando mayúsculas/minúsculas.");
        List<Tag> tags = tagService.tagQueComienzan("sil");
        System.out.println(tags);
        //Actividad 1.10 Devuelve un conteo de cuántos espacios hay por cada combinación de ciudad (del Venue) y Tag.
        System.out.println("Actividad 1.10");
        List<SpaceByVenueTagDto> spaces = spaceService.spacePorVenueTag();
        System.out.println(spaces);
    }


}
