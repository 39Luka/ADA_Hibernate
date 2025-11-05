package org.example.dao.hibernate;

import org.example.Actividad1.AccessCard;
import org.example.dao.AccessCardDao;

public class AccessCardDaoImpl extends GenericDaoImpl<AccessCard,Long> implements AccessCardDao {
    public AccessCardDaoImpl() {
        super(AccessCard.class);
    }
}
