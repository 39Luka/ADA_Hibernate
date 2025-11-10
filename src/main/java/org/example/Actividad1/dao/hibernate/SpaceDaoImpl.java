package org.example.Actividad1.dao.hibernate;

import org.example.Actividad1.domain.BookingStatus;
import org.example.Actividad1.domain.Space;
import org.example.Actividad1.dao.SpaceDao;
import org.example.Actividad1.dto.MostProfitSpacesDto;
import org.hibernate.Session;

import java.util.List;


public class SpaceDaoImpl extends GenericDaoImpl<Space,Long> implements SpaceDao {
    public SpaceDaoImpl() {
        super(Space.class);
    }

    @Override
    public List<MostProfitSpacesDto> findTop3MostProfitSpaces(Session session) {
        String query = "select " +
                "new org.example.Actividad1.dto.MostProfitSpacesDto(b.space.code, b.space.name, cast(sum(b.totalPrice) as java.math.BigDecimal)) " +
                "from Booking b " +
                "where b.status = :status " +
                "group by b.space.code, b.space.name " +
                "order by sum(b.totalPrice) desc";


        return session.createQuery(query, MostProfitSpacesDto.class)
                .setParameter("status", BookingStatus.CONFIRMADO)
                .setMaxResults(3)
                .getResultList();
    }

    @Override
    public List<Space> encontrarEspacionNuncaReservados(Session session) {
        String query = "SELECT s.* " +
                "FROM spaces s " +
                "LEFT JOIN bookings b on s.id = b.space_id " +
                "WHERE b.space_id IS NULL";

        return session.createNativeQuery(query,Space.class).getResultList();
    }


    @Override
    public List<String> top5CiudadesMasEspacios(Session session) {
        String query = "select s.venue.city from Space s group by s.venue.city order by count(s.id) desc";
        return session.createQuery(query, String.class)
                .setMaxResults(5)
                .getResultList();
    }


}
