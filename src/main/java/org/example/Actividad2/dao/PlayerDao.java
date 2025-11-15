package org.example.Actividad2.dao;

import org.example.Actividad2.domain.Player;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface PlayerDao extends GenericDao<Player,Long> {

    public List<Player> inactivosQueJugaronEn(Session s, LocalDateTime fecha);

    public List<Player> jugadoresNLogros (Session s, int numLogros);
}
