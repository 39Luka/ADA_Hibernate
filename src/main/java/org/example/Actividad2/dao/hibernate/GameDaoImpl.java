package org.example.Actividad2.dao.hibernate;

import org.example.Actividad2.dao.GameDao;
import org.example.Actividad2.domain.Game;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public class GameDaoImpl extends GenericDaoImpl<Game,Long> implements GameDao {
    public GameDaoImpl() {
        super(Game.class, Long.class);
    }

    @Override
    public List<Game> gamesMatchesRango(Session session, LocalDateTime start, LocalDateTime end) {
        String query = "select m.cabinet.game " +
                "from Match m " +
                "where m.startAt >= :start and m.endAt <= :end " +
                "group by m.cabinet.game " +
                "order by count(m) desc";

        return session.createQuery(query, Game.class)
                .setParameter("start",start)
                .setParameter("end",end)
                .getResultList();
    }
}
