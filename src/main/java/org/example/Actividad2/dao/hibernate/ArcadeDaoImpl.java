package org.example.Actividad2.dao.hibernate;

import org.example.Actividad2.dto.ArcadeIncomeDto;
import org.example.Actividad2.dao.ArcadeDao;
import org.example.Actividad2.domain.Arcade;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public class ArcadeDaoImpl extends GenericDaoImpl<Arcade,Long> implements ArcadeDao {
    public ArcadeDaoImpl() {
        super(Arcade.class, Long.class);
    }

    @Override
    public List<Arcade> arcadesPorNombre(Session session, String name) {
        return session.createNamedQuery("Arcade.byName", Arcade.class)
                .setParameter("name", name.toLowerCase())
                .getResultList();
    }

    @Override
    public List<ArcadeIncomeDto> ingresosEstimadosArcades(Session session, LocalDateTime start, LocalDateTime end) {
        String query = """
    select a.name as arcade,
           coalesce(sum(c.hourly_cost * TIMESTAMPDIFF(HOUR, :start, :end)), 0) as income
    from arcade a
    join cabinet c on a.id = c.arcade_id
    group by a.id
""";


        List<Object[]> rows = session.createNativeQuery(query, Object[].class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();

        return rows.stream()
                .map(r -> new ArcadeIncomeDto(
                        (String) r[0],               // arcade
                        ((Number) r[1]).longValue()  // income
                ))
                .toList();
    }


}
