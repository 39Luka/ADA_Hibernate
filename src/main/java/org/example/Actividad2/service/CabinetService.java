package org.example.Actividad2.service;

import org.example.Actividad2.dao.ArcadeDao;
import org.example.Actividad2.dao.CabinetDao;
import org.example.Actividad2.dao.GameDao;
import org.example.Actividad2.dao.hibernate.ArcadeDaoImpl;
import org.example.Actividad2.dao.hibernate.CabinetDaoImpl;
import org.example.Actividad2.dao.hibernate.GameDaoImpl;
import org.example.Actividad2.domain.Arcade;
import org.example.Actividad2.domain.Cabinet;
import org.example.Actividad2.domain.Game;
import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CabinetService {


    private final SessionFactory sf;
    private final CabinetDao cabinetDao;
    private final ArcadeDao arcadeDao;
    private final GameDao gameDao;

    public CabinetService(SessionFactory sf) {
        this.sf = sf;
        this.cabinetDao = new CabinetDaoImpl();
        this.gameDao = new GameDaoImpl();
        this.arcadeDao = new ArcadeDaoImpl();
    }

    public void asignarCabinet(Long cabinetId, Long arcadeId,Long gameId){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();

            Cabinet cabinet  = cabinetDao.findById(s, cabinetId);
            if (cabinet == null) throw  new PersistentObjectException("No existe el cabinet");

            Arcade arcade = arcadeDao.findById(s,arcadeId);
            if(arcade == null) throw new PersistentObjectException("No existe el arcade");

            Game game = gameDao.findById(s,gameId);
            if(game == null) throw new PersistentObjectException("No existe el juego");

            cabinet.setGame(game);
            cabinet.setArcade(arcade);

            cabinetDao.update(s, cabinet);


            tx.commit();

        }catch (PersistentObjectException e){
            if(tx != null) tx.rollback();
            throw e;

        }
    }


}
