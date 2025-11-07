package org.example.Actividad1.dao.hibernate;

import org.example.Actividad1.domain.Space;
import org.example.Actividad1.dao.SpaceDao;


public class SpaceDaoImpl extends GenericDaoImpl<Space,Long> implements SpaceDao {
    public SpaceDaoImpl() {
        super(Space.class);
    }

}
