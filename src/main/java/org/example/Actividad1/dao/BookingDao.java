package org.example.Actividad1.dao;

import org.example.Actividad1.domain.Booking;
import org.example.Actividad1.domain.Venue;
import org.example.Actividad1.dto.VenueIncome;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingDao extends GenericDao<Booking, Long> {

    List<VenueIncome> venuesConIngresosEntreFechas(Session session, LocalDateTime start, LocalDateTime end);

}
