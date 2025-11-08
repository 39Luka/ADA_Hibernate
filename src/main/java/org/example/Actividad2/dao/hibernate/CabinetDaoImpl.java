package org.example.Actividad2.dao.hibernate;

import org.example.Actividad2.dao.CabinetDao;
import org.example.Actividad2.dao.GenericDao;
import org.example.Actividad2.domain.Cabinet;

public class CabinetDaoImpl extends GenericDaoImpl<Cabinet,Long> implements CabinetDao {
    public CabinetDaoImpl() {
        super(Cabinet.class);
    }
}
