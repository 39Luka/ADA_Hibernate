package org.example.Actividad2.dao.hibernate;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.example.Actividad2.dao.PlayerDao;
import org.example.Actividad2.domain.Match;
import org.example.Actividad2.domain.Player;
import org.example.Actividad2.domain.RfidCard;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public class PlayerDaoImpl extends GenericDaoImpl<Player,Long> implements PlayerDao {
    public PlayerDaoImpl() {
        super(Player.class, Long.class);
    }

    @Override
    public List<Player> inactivosQueJugaronEn(Session s, LocalDateTime fecha) {
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Player> cq = cb.createQuery(Player.class);
        Root<Player> root = cq.from(Player.class);
        Join<Player, RfidCard> rfidCardJoin = root.join("rfidCard");
        Join<Player, Match> matchJoin = root.join("matches");


        cq.select(root)
                .distinct(true)
                .where(
                        cb.and(
                                cb.isFalse(rfidCardJoin.get("active")),
                                cb.equal(matchJoin.get("startedAt"), fecha)
                        )
                );

        return s.createQuery(cq).getResultList();
    }

    @Override
    public List<Player> jugadoresNLogros(Session s, int numLogros) {
        String query = """
    select p
    from Player p
    left join p.achievements a
    group by p
    having coalesce(count(a), 0) >= :numLogros
""";

        return s.createQuery(query, Player.class)
                .setParameter("numLogros", numLogros)
                .getResultList();
    }
}
