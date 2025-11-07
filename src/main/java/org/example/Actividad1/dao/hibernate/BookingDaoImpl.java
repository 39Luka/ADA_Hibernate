package org.example.Actividad1.dao.hibernate;

import org.example.Actividad1.domain.Booking;
import org.example.Actividad1.dao.BookingDao;

public class BookingDaoImpl extends GenericDaoImpl<Booking,Long> implements BookingDao {
    public BookingDaoImpl() {
        super(Booking.class);
    }
}
