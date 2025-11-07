package org.example.Actividad1.dao.hibernate;

import org.example.Actividad1.domain.AccessCard;
import org.example.Actividad1.dao.AccessCardDao;

public class AccessCardDaoImpl extends GenericDaoImpl<AccessCard,Long> implements AccessCardDao {
    public AccessCardDaoImpl() {
        super(AccessCard.class);
    }
}
