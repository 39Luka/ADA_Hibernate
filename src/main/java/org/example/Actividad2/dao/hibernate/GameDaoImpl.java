package org.example.Actividad2.dao.hibernate;

import org.example.Actividad2.dao.GameDao;
import org.example.Actividad2.domain.Game;
import org.example.Actividad2.dto.ArchivementGenreDto;
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
                "where m.startedAt >= :start and m.endAt <= :end " +
                "group by m.cabinet.game " +
                "order by count(m) desc";

        return session.createQuery(query, Game.class)
                .setParameter("start",start)
                .setParameter("end",end)
                .getResultList();
    }

    @Override
    public List<ArchivementGenreDto> logrosGenero(Session s) {
        String query = """
    select new org.example.Actividad2.dto.ArchivementGenreDto(g.genre, count(a))
    from Player p
    join p.achievements a
    join a.game g
    group by g.genre
""";


        return s.createQuery(query,ArchivementGenreDto.class)
                .getResultList();
    }
}
