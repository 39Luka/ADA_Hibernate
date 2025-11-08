package org.example.Actividad2.service;

import org.example.Actividad2.dao.PlayerDao;
import org.example.Actividad2.dao.RfidCardDao;
import org.example.Actividad2.dao.hibernate.PlayerDaoImpl;
import org.example.Actividad2.dao.hibernate.RfidCardDaoImpl;
import org.example.Actividad2.domain.Player;
import org.example.Actividad2.domain.RfidCard;
import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class PlayerService {

    private final SessionFactory sf;
    private final PlayerDao playerDao;
    private final RfidCardDao rfidCardDao;


    public PlayerService(SessionFactory sf) {
        this.sf = sf;
        this.playerDao = new PlayerDaoImpl();
        this.rfidCardDao = new RfidCardDaoImpl();
    }

    public Long emitirRfidCard(Long playerId, String cardUid){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx= s.beginTransaction();

            Player player = playerDao.findById(s,playerId);
            if (player == null) throw new PersistentObjectException("No existe el jugador");

            RfidCard rfidCard = new RfidCard();
            rfidCard.setActive(true);
            rfidCard.setUid(cardUid);
            rfidCard.setIssuedAt(LocalDateTime.now());
            rfidCard.setPlayer(player);

            Long cardId = rfidCardDao.save(s, rfidCard);

            tx.commit();

            return cardId;
        }catch (PersistentObjectException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    public void activarDesactivarTarjeta(Long cardId){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx= s.beginTransaction();

            RfidCard rfidCard = rfidCardDao.findById(s,cardId);
            if (rfidCard == null) throw new PersistentObjectException("No existe la tarjeta");

            boolean activo = rfidCard.isActive();
            rfidCard.setActive(!activo);

            rfidCardDao.update(s,rfidCard);

            tx.commit();


        }catch (PersistentObjectException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
}
