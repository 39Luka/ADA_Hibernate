package org.example.Actividad1.dao.hibernate;

import org.example.Actividad1.domain.Booking;
import org.example.Actividad1.dao.BookingDao;
import org.example.Actividad1.domain.BookingStatus;
import org.example.Actividad1.domain.Venue;
import org.example.Actividad1.dto.VenueIncome;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public class BookingDaoImpl extends GenericDaoImpl<Booking,Long> implements BookingDao {
    public BookingDaoImpl() {
        super(Booking.class);
    }

    @Override
    public List<VenueIncome> venuesConIngresosEntreFechas(Session session, LocalDateTime start, LocalDateTime end) {
        String query = "select " +
                "new org.example.Actividad1.dto.VenueIncome(b.space.venue.name, sum(b.totalPrice)) " +
                "from Booking b " +
                "where b.startTime >= :start AND b.endTime <= :end AND b.status = :status "+
                "group by b.space.venue.name";

        return session.createQuery(query, VenueIncome.class)
                .setParameter("start", start)
                .setParameter("end",end)
                .setParameter("status", BookingStatus.CONFIRMADO)
                .getResultList();
    }
}
