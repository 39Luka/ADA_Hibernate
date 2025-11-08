package org.example.Actividad2.dao.hibernate;

import org.example.Actividad2.dao.RfidCardDao;
import org.example.Actividad2.domain.RfidCard;

public class RfidCardDaoImpl extends GenericDaoImpl<RfidCard, Long> implements RfidCardDao {
    public RfidCardDaoImpl() {
        super(RfidCard.class);
    }
}
