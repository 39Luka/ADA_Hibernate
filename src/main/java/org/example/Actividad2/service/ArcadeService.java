package org.example.Actividad2.service;

import jakarta.persistence.PersistenceException;
import org.example.Actividad2.dto.ArcadeIncomeDto;
import org.example.Actividad2.dao.ArcadeDao;
import org.example.Actividad2.dao.hibernate.ArcadeDaoImpl;
import org.example.Actividad2.domain.Arcade;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class ArcadeService {
    private SessionFactory sf;
    private ArcadeDao arcadeDao;

    public ArcadeService(SessionFactory sf) {
        this.sf = sf;
        this.arcadeDao = new ArcadeDaoImpl();
    }

    public List<Arcade> arcadePorNombre(String name){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx = s.beginTransaction();

            List<Arcade> arcades = arcadeDao.arcadesPorNombre(s,name);

            tx.commit();
            return arcades;
        }catch (PersistenceException e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    public List<ArcadeIncomeDto> arcadeIngresosEstimados(LocalDateTime start, LocalDateTime end){
        Transaction tx = null;
        try{
            Session s = sf.getCurrentSession();
            tx= s.beginTransaction();

            List<ArcadeIncomeDto> arcade = arcadeDao.ingresosEstimadosArcades(s, start,end);

            tx.commit();
            return arcade;
        } catch (PersistenceException e) {
            if(tx != null) tx.rollback();
            throw e;
        }
    }


}
