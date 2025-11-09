package org.example.Actividad2;

import org.example.Actividad2.dao.hibernate.*;
import org.example.Actividad2.domain.*;
import org.example.Actividad2.service.CabinetService;
import org.example.Actividad2.service.PlayerService;
import org.example.Actividad2.service.GenericCRUDService;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil2.getSessionFactory();

        GenericCRUDService<Arcade, Long> arcadeService = new GenericCRUDService<>(sf, Arcade.class, new ArcadeDaoImpl());
        Arcade arcade = new Arcade();
        arcade.setAddress("Calle");
        arcade.setName("Nombre Arcade");
        Long arcadeID = arcadeService.create(arcade);
        System.out.println("Arcade ID generado: " + arcadeID);

        GenericCRUDService<Game, Long> gameService = new GenericCRUDService<>(sf, Game.class, new GameDaoImpl());
        Game game = new Game();
        game.setName("Nombre Juego");
        game.setCode("AAA");
        game.setReleaseYear(2025);
        Long gameID = gameService.create(game);
        System.out.println("Game ID generado: " + gameID);

        GenericCRUDService<Cabinet, Long> cabinetService = new GenericCRUDService<>(sf, Cabinet.class, new CabinetDaoImpl());
        Cabinet cabinet = new Cabinet();
        cabinet.setStatus(Status.ACTIVE);
        cabinet.setBuildYear(2025);
        cabinet.setSlug("slug");
        cabinet.setArcade(arcade);  // Evita null
        cabinet.setGame(game);      // Evita null
        Long cabinetID = cabinetService.create(cabinet);
        System.out.println("Cabinet ID generado: " + cabinetID);

        CabinetService cabinetService1 = new CabinetService(sf);
        cabinetService1.asignarCabinet(cabinetID, arcadeID, gameID);

        GenericCRUDService<Player, Long> playerService = new GenericCRUDService<>(sf, Player.class, new PlayerDaoImpl());
        Player player = new Player();
        player.setEmail("email");
        player.setNickname("nick");
        player.setCreatedAt(LocalDateTime.now());
        Long playerID = playerService.create(player);
        System.out.println("Player ID generado: " + playerID);

        PlayerService playerService1 = new PlayerService(sf);
        Long rfidID = playerService1.emitirRfidCard(playerID, "CARD");
        System.out.println("RfidCard ID generado: " + rfidID);

        GenericCRUDService<Tag, Long> tagService = new GenericCRUDService<>(sf, Tag.class, new TagDaoImpl());
        Tag tag1 = new Tag();
        tag1.setName("tag1");
        Tag tag2 = new Tag();
        tag2.setName("tag2");
        Long tag1ID = tagService.create(tag1);
        Long tag2ID = tagService.create(tag2);
        System.out.println("Tag1 ID generado: " + tag1ID);
        System.out.println("Tag2 ID generado: " + tag2ID);

        cabinetService1.addTag(cabinetID, tag1ID);
        cabinetService1.addTag(cabinetID, tag2ID);

        GenericCRUDService<Match, Long> matchService = new GenericCRUDService<>(sf, Match.class, new MatchDaoImpl());

        Match match1 = new Match();
        match1.setPlayer(playerService.findById(playerID));
        match1.setCabinet(cabinetService.findById(cabinetID));
        match1.setResult(Result.DRAW);
        match1.setScore(8);
        match1.setDurationSec(8);
        match1.setCreditsUsed(7);
        match1.setStartedAt(LocalDateTime.now());

        Match match2 = new Match();
        match2.setPlayer(playerService.findById(playerID));
        match2.setCabinet(cabinetService.findById(cabinetID));
        match2.setResult(Result.DRAW);
        match2.setScore(8);
        match2.setDurationSec(8);
        match2.setCreditsUsed(7);
        match2.setStartedAt(LocalDateTime.now());

        Long match1ID = matchService.create(match1);
        Long match2ID = matchService.create(match2);
        System.out.println("Match1 ID generado: " + match1ID);
        System.out.println("Match2 ID generado: " + match2ID);

        Cabinet readCabinet = cabinetService.findById(cabinetID);
        System.out.println("Cabinet leído: " + readCabinet);

        readCabinet.setStatus(Status.MAINTENANCE);
        cabinetService.update(readCabinet);

        cabinetService1.removeTag(cabinetID, tag1ID);
        cabinetService1.removeTag(cabinetID, tag2ID);

        matchService.deleteById(match1ID);
        matchService.deleteById(match2ID);

        tagService.deleteById(tag1ID);
        tagService.deleteById(tag2ID);

        playerService1.retirarCard(rfidID);
        playerService.deleteById(playerID);

        cabinetService.deleteById(cabinetID);
        gameService.deleteById(gameID);
        arcadeService.deleteById(arcadeID);

        System.out.println("Secuencia completada con éxito.");
    }
}
