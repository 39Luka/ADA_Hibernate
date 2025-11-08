package org.example.Actividad2.dao.hibernate;

import org.example.Actividad2.dao.GameDao;
import org.example.Actividad2.domain.Game;

public class GameDaoImpl extends GenericDaoImpl<Game,Long> implements GameDao {
    public GameDaoImpl() {
        super(Game.class);
    }
}
