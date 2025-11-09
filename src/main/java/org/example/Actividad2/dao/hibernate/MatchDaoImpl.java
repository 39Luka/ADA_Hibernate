package org.example.Actividad2.dao.hibernate;

import org.example.Actividad2.dao.MatchDao;
import org.example.Actividad2.domain.Match;

public class MatchDaoImpl extends GenericDaoImpl<Match,Long> implements MatchDao {
    public MatchDaoImpl() {
        super(Match.class, Long.class);
    }
}
