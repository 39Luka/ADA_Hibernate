package org.example.Actividad2.dao.hibernate;

import org.example.Actividad2.dao.PlayerDao;
import org.example.Actividad2.domain.Player;

public class PlayerDaoImpl extends GenericDaoImpl<Player,Long> implements PlayerDao {
    public PlayerDaoImpl() {
        super(Player.class);
    }
}
