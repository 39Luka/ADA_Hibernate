package org.example.Actividad2;

import org.example.Actividad2.domain.*;
import org.example.Actividad2.dto.ArcadeIncomeDto;
import org.example.Actividad2.dto.ArchivementGenreDto;
import org.example.Actividad2.dto.CabinetPorGeneroDto;
import org.example.Actividad2.service.ArcadeService;
import org.example.Actividad2.service.CabinetService;
import org.example.Actividad2.service.GameService;
import org.example.Actividad2.service.PlayerService;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;
import java.util.List;

public class ConsultasHibernate {
    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil2.getSessionFactory();

        //Actividad 2.1 Arcades por nombres
        System.out.println("Actividad 2.1 Arcades por nombres");
        ArcadeService arcadeService = new ArcadeService(sf);
        List<Arcade> arcades = arcadeService.arcadePorNombre("arcade galaxy");
        System.out.println(arcades);
        //Actividad 2.2 Top juegos por nº de partidas en rango de fechas
        System.out.println("Actividad 2.2 Top juegos por nº de partidas en rango de fechas");
        GameService gameService = new GameService(sf);
        List<Game> games = gameService.topGamesByMatchInRange(LocalDateTime.of(2025,11,10,0,0),LocalDateTime.of(2025,11,11,0,0));
        System.out.println(games);
        //Actividad 2.3 Ingresos estimados por arcade
        System.out.println("Actividad 2.3 Ingresos estimados por arcade");
        List<ArcadeIncomeDto> arcade =arcadeService.arcadeIngresosEstimados(LocalDateTime.now(),LocalDateTime.now().plusHours(8));
        System.out.println(arcade);
        //Actividad 2.4 Cabinets activos por género
        System.out.println("Actividad 2.4 Cabinets activos por género");
        CabinetService cabinetService = new CabinetService(sf);
        List<CabinetPorGeneroDto> cabinet = cabinetService.cabinetPorGenero();
        System.out.println(cabinet);
        //Actividad 2.5 Jugadores con tarjeta inactiva pero partidas recientes. Devuelve Players que tengan alguna Card inactiva y hayan jugado desde una fecha pasada por parámetro.
        System.out.println("Actividad 2.5 ");
        PlayerService playerService = new PlayerService(sf);
        List<Player> players = playerService.playerInactivosJugaronEn(LocalDateTime.now());
        System.out.println(players);
        //Actividad 2.6 Jugadores con al menos N logros
        System.out.println("Actividad 2.6 Jugadores con al menos N logros");
        List<Player> players1 = playerService.jugadoresConLogros(1);
        System.out.println(players);
        //Actividad 2.7 Ranking de logros por género
        System.out.println("Actividad 2.7 Ranking de logros por género");
        List<ArchivementGenreDto> archivementGenreDtos = gameService.archivementGenre();
        System.out.println(archivementGenreDtos);
        //Actividad 2.8 Cabinets sin partidas desde la última X fecha. Se pasará una fecha por parámetro.
        System.out.println("Actividad 2.8 Cabinets sin partidas desde la última X fecha. Se pasará una fecha por parámetro.");
        List<Cabinet> cabinets = cabinetService.sinPartidas(LocalDateTime.now());
        System.out.println(cabinets);
    }
}
