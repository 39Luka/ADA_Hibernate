package org.example.Actividad1.dao.hibernate;

import com.mysql.cj.util.SaslPrep;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.example.Actividad1.domain.BookingStatus;
import org.example.Actividad1.domain.Space;
import org.example.Actividad1.dao.SpaceDao;
import org.example.Actividad1.domain.Tag;
import org.example.Actividad1.dto.MostProfitSpacesDto;
import org.example.Actividad1.dto.SpaceByVenueTagDto;
import org.hibernate.Session;

import java.math.BigDecimal;
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

    @Override
    public List<SpaceByVenueTagDto> spacePorVenueTag(Session session) {
        String query = "select new org.example.Actividad1.dto.SpaceByVenueTagDto(s.venue.name, t.name, count(s)) " +
                "from Space s " +
                "join s.tags t " +
                "group by s.venue.name, t.name";

        return session.createQuery(query, SpaceByVenueTagDto.class)
                .getResultList();
    }

    @Override
    public List<Space> spaceElxWifi(Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Space> cq = cb.createQuery(Space.class);
        Root<Space> root = cq.from(Space.class);
        Join<Space, Tag> tagJoin = root.join("tags");

        cq.select(root)
                .where(
                     cb.and(
                            cb.like(root.get("name"), "%ELX%"),
                            cb.equal(tagJoin.get("name"), "wifi")

                         )
        );

        return session.createQuery(cq).getResultList();
    }

    @Override
    public List<Space> activeCapMinPrecioMax(Session session, int capacidadMin, BigDecimal precioMax) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Space> cq = cb.createQuery(Space.class);
        Root<Space> spaceRoot = cq.from(Space.class);

        cq.select(spaceRoot)
                .where(
                        cb.and(
                        cb.isTrue(spaceRoot.get("active")), cb.ge(spaceRoot.get("capacity"), capacidadMin), cb.le(spaceRoot.get("hourlyPrice"), precioMax)
                        )
                ).orderBy(cb.asc(spaceRoot.get("hourlyPrice")));

        return session.createQuery(cq).getResultList();
    }


}
