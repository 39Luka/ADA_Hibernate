package org.example.dao.hibernate;

import org.example.Actividad1.Booking;
import org.example.dao.BookingDao;

public class BookingDaoImpl extends GenericDaoImpl<Booking,Long> implements BookingDao {
    public BookingDaoImpl(Class<Booking> entityClass) {
        super(entityClass);
    }
}
