package org.example.Actividad1.dao;

import org.example.Actividad1.domain.Booking;
import org.example.Actividad1.domain.Space;
import org.example.Actividad1.dto.VenueIncomeDto;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingDao extends GenericDao<Booking, Long> {

    List<VenueIncomeDto> venuesConIngresosEntreFechas(Session session, LocalDateTime start, LocalDateTime end);

    List<Booking> bookingsConfirmedByRange(Session session, LocalDateTime start, LocalDateTime end);

    List<Space> top3SpaceConfirmedIncomes(Session session);
}
