package org.example.Actividad2.dao;

import org.example.Actividad2.domain.Game;
import org.example.Actividad2.dto.ArchivementGenreDto;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface GameDao extends GenericDao<Game,Long>{

    List<Game> gamesMatchesRango(Session session, LocalDateTime start, LocalDateTime end);
    List<ArchivementGenreDto> logrosGenero(Session s);
}


