package org.example.Actividad2.service;

import jakarta.persistence.PersistenceException;
import org.example.Actividad2.dao.GameDao;
import org.example.Actividad2.dao.hibernate.GameDaoImpl;
import org.example.Actividad2.domain.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class GameService {
    private SessionFactory sf;
    private GameDao gameDao;

    public GameService(SessionFactory sf) {
        this.sf = sf;
        this.gameDao = new GameDaoImpl();
    }

    public List<Game> topGamesByMatchInRange(LocalDateTime start,LocalDateTime end){
        Transaction tx =  null;
        try{
            Session session = sf.getCurrentSession();
            tx = session.beginTransaction();
            List<Game> games = gameDao.gamesMatchesRango(session,start,end);
            tx.commit();
            return games;
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }
}
