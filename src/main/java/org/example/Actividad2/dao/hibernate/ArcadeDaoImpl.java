package org.example.Actividad2.dao.hibernate;

import org.example.Actividad2.dao.ArcadeDao;
import org.example.Actividad2.domain.Arcade;

public class ArcadeDaoImpl extends GenericDaoImpl<Arcade,Long> implements ArcadeDao {
    public ArcadeDaoImpl() {
        super(Arcade.class);
    }
}
