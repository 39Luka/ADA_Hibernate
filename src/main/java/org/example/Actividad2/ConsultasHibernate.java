package org.example.Actividad2;

import org.example.Actividad2.domain.Arcade;
import org.example.Actividad2.domain.HibernateUtil2;
import org.example.Actividad2.service.ArcadeService;
import org.hibernate.SessionFactory;

import java.util.List;

public class ConsultasHibernate {
    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil2.getSessionFactory();

        //Actividad 2.1 Arcades por nombres
        ArcadeService arcadeService = new ArcadeService(sf);
        List<Arcade> arcades = arcadeService.arcadePorNombre("arcade galaxy");
        System.out.println(arcades);
        //Actividad 2.2 Top juegos por nº de partidas en rango de fechas
    }
}
