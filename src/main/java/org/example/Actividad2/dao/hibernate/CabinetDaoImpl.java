package org.example.Actividad2.dao.hibernate;

import jakarta.persistence.criteria.*;
import org.example.Actividad2.dao.CabinetDao;
import org.example.Actividad2.domain.Cabinet;
import org.example.Actividad2.domain.Match;
import org.example.Actividad2.domain.Status;
import org.example.Actividad2.dto.CabinetPorGeneroDto;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public class CabinetDaoImpl extends GenericDaoImpl<Cabinet,Long> implements CabinetDao {
    public CabinetDaoImpl() {
        super(Cabinet.class, Long.class);
    }


    @Override
    public List<CabinetPorGeneroDto> cabinetPorGenero(Session s) {
        String query = """
            select new org.example.Actividad2.dto.CabinetPorGeneroDto(c.game.genre, count(c))
            from Cabinet c
            where c.status = :status
            group by c.game.genre
            """;

        return s.createQuery(query, CabinetPorGeneroDto.class)
                .setParameter("status", Status.ACTIVE)
                .getResultList();
    }

    @Override
    public List<Cabinet> sinPartidaDesde(Session s, LocalDateTime fecha) {
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Cabinet> cq = cb.createQuery(Cabinet.class);
            Root<Cabinet> cabinetRoot = cq.from(Cabinet.class);
            Join<Cabinet, Match> matchJoin = cabinetRoot.join("matches", JoinType.LEFT);


            cq.select(cabinetRoot).distinct(true)
                    .where(cb.or(
                            cb.isNull(matchJoin.get("id")),
                            cb.not(cb.greaterThanOrEqualTo(matchJoin.get("startedAt"), fecha))
                    ));

            return s.createQuery(cq).getResultList();
    }

}


