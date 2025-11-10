package org.example.Actividad1.dao.hibernate;

import org.example.Actividad1.domain.Booking;
import org.example.Actividad1.dao.BookingDao;
import org.example.Actividad1.domain.BookingStatus;
import org.example.Actividad1.domain.Space;
import org.example.Actividad1.dto.VenueIncomeDto;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public class BookingDaoImpl extends GenericDaoImpl<Booking,Long> implements BookingDao {
    public BookingDaoImpl() {
        super(Booking.class);
    }

    @Override
    public List<VenueIncomeDto> venuesConIngresosEntreFechas(Session session, LocalDateTime start, LocalDateTime end) {
        String query = "select " +
                "new org.example.Actividad1.dto.VenueIncomeDto(b.space.venue.name, sum(b.totalPrice)) " +
                "from Booking b " +
                "where b.startTime >= :start AND b.endTime <= :end AND b.status = :status "+
                "group by b.space.venue.name";

         List<VenueIncomeDto> lista =  session.createQuery(query, VenueIncomeDto.class)
                .setParameter("start", start)
                .setParameter("end",end)
                .setParameter("status", BookingStatus.CONFIRMADO)
                .getResultList();

        return lista;
    }

    @Override
    public List<Booking> bookingsConfirmedByRange(Session session, LocalDateTime start, LocalDateTime end) {
        return session.createNamedQuery("Booking.confirmedByRangeVenue", Booking.class)
                .setParameter("start", start)
                .setParameter("end",end)
                .setParameter("status", BookingStatus.CONFIRMADO)
                .getResultList();

    }

    @Override
    public List<Space> top3SpaceConfirmedIncomes(Session session) {
        String query = "select b.space from Booking b " +
                "where status = :status" +
                " group by b.space" +
                " order by sum(b.totalPrice) desc";


        return session.createQuery(query,Space.class)
                .setParameter("status", BookingStatus.CONFIRMADO)
                .setMaxResults(3)
                .getResultList();
    }


}
