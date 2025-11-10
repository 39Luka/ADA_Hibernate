package org.example.Actividad2.dao.hibernate;

import org.example.Actividad2.dao.ArcadeDao;
import org.example.Actividad2.domain.Arcade;
import org.hibernate.Session;

import java.util.List;

public class ArcadeDaoImpl extends GenericDaoImpl<Arcade,Long> implements ArcadeDao {
    public ArcadeDaoImpl() {
        super(Arcade.class, Long.class);
    }

    @Override
    public List<Arcade> arcadesPorNombre(Session session, String name) {
        return session.createNamedQuery("Arcade.byName",Arcade.class)
                .setParameter("name", name.toLowerCase())
                .getResultList();
    }
}
