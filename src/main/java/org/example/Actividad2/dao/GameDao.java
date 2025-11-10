package org.example.Actividad2.dao;

import org.example.Actividad2.domain.Game;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface GameDao extends GenericDao<Game,Long>{

    List<Game> gamesTopMatchesRango(Session session, LocalDateTime start, LocalDateTime end);

}


