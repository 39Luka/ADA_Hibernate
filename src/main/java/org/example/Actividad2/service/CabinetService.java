package org.example.Actividad2.service;

import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CabinetService {


    private final SessionFactory sf;


    public CabinetService(SessionFactory sf) {
        this.sf = sf;
    }

    public void asignarCabinet(Long cabinetId, Long arcadeId,Long gameId){
        Transaction tx = null;
        try{

        }catch (PersistentObjectException e){

        }
    }

}
