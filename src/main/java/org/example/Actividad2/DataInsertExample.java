package org.example.Actividad2;

import org.example.Actividad2.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class DataInsertExample {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil2.getSessionFactory(); // tu util de Hibernate
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            // --- 1️⃣ Arcades ---
            Arcade arcade1 = new Arcade();
            arcade1.setName("Arcade Galaxy");
            arcade1.setAddress("Calle Falsa 123, Madrid");
            session.persist(arcade1);

            Arcade arcade2 = new Arcade();
            arcade2.setName("Pixel Palace");
            arcade2.setAddress("Av. de los Juegos 45, Barcelona");
            session.persist(arcade2);

            // --- 2️⃣ Games ---
            Game pacman = new Game();
            pacman.setCode("PACMAN");
            pacman.setName("Pac-Man");
            pacman.setReleaseYear(1980);
            pacman.setGenre("Arcade");
            session.persist(pacman);

            Game tetris = new Game();
            tetris.setCode("TETRIS");
            tetris.setName("Tetris");
            tetris.setReleaseYear(1984);
            tetris.setGenre("Puzzle");
            session.persist(tetris);

            Game sf2 = new Game();
            sf2.setCode("STREETFIGHTER");
            sf2.setName("Street Fighter II");
            sf2.setReleaseYear(1991);
            sf2.setGenre("Fighting");
            session.persist(sf2);

            // --- 3️⃣ Tags ---
            Tag classic = new Tag();
            classic.setName("Classic");
            session.persist(classic);

            Tag multiplayer = new Tag();
            multiplayer.setName("Multiplayer");
            session.persist(multiplayer);

            Tag puzzle = new Tag();
            puzzle.setName("Puzzle");
            session.persist(puzzle);

            Tag fighting = new Tag();
            fighting.setName("Fighting");
            session.persist(fighting);

            // --- 4️⃣ Cabinets ---
            Cabinet cabinet1 = new Cabinet();
            cabinet1.setSlug("cabinet-001");
            cabinet1.setBuildYear(1985);
            cabinet1.setStatus(Status.ACTIVE);
            cabinet1.setHourly_cost(5L);
            cabinet1.setArcade(arcade1);
            cabinet1.setGame(pacman);
            cabinet1.getTags().add(classic);
            cabinet1.getTags().add(multiplayer);
            session.persist(cabinet1);

            Cabinet cabinet2 = new Cabinet();
            cabinet2.setSlug("cabinet-002");
            cabinet2.setBuildYear(1986);
            cabinet2.setStatus(Status.MAINTENANCE);
            cabinet2.setHourly_cost(6L);
            cabinet2.setArcade(arcade1);
            cabinet2.setGame(tetris);
            cabinet2.getTags().add(classic);
            cabinet2.getTags().add(puzzle);
            session.persist(cabinet2);

            Cabinet cabinet3 = new Cabinet();
            cabinet3.setSlug("cabinet-003");
            cabinet3.setBuildYear(1992);
            cabinet3.setStatus(Status.ACTIVE);
            cabinet3.setHourly_cost(7L);
            cabinet3.setArcade(arcade2);
            cabinet3.setGame(sf2);
            cabinet3.getTags().add(multiplayer);
            cabinet3.getTags().add(fighting);
            session.persist(cabinet3);

            // --- 5️⃣ Players y RFID ---
            Player player1 = new Player();
            player1.setNickname("playerOne");
            player1.setEmail("player1@email.com");
            player1.setCreatedAt(LocalDateTime.now());
            session.persist(player1);

            RfidCard card1 = new RfidCard();
            card1.setUid("UID123456");
            card1.setActive(true);
            card1.setIssuedAt(LocalDateTime.now());
            card1.setPlayer(player1);
            player1.setRfidCard(card1);
            session.persist(card1);

            Player player2 = new Player();
            player2.setNickname("playerTwo");
            player2.setEmail("player2@email.com");
            player2.setCreatedAt(LocalDateTime.now());
            session.persist(player2);

            RfidCard card2 = new RfidCard();
            card2.setUid("UID654321");
            card2.setActive(true);
            card2.setIssuedAt(LocalDateTime.now());
            card2.setPlayer(player2);
            player2.setRfidCard(card2);
            session.persist(card2);

            // --- 6️⃣ Matches ---
            Match match1 = new Match();
            match1.setStartedAt(LocalDateTime.now());
            match1.setDurationSec(300);
            match1.setScore(1500);
            match1.setResult(Result.WIN);
            match1.setCreditsUsed(3);
            match1.setEndAt(LocalDateTime.now().plusMinutes(5));
            match1.setPlayer(player1);
            match1.setCabinet(cabinet1);
            session.persist(match1);

            Match match2 = new Match();
            match2.setStartedAt(LocalDateTime.now());
            match2.setDurationSec(200);
            match2.setScore(900);
            match2.setResult(Result.LOSE);
            match2.setCreditsUsed(2);
            match2.setEndAt(LocalDateTime.now().plusMinutes(3));
            match2.setPlayer(player2);
            match2.setCabinet(cabinet2);
            session.persist(match2);

            tx.commit();
            System.out.println("Datos insertados correctamente ✅");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
