package org.example.Actividad1;

import org.example.Actividad1.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DataInsertExample {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil1.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            // 1️⃣ Crear Users
            User user1 = new User(LocalDateTime.now(), "Silvia Cachón", "silvia@example.com");
            User user2 = new User(LocalDateTime.now(), "Juan Pérez", "juan@example.com");
            session.persist(user1);
            session.persist(user2);

            // 2️⃣ Crear AccessCards
            AccessCard card1 = new AccessCard();
            card1.setActive(true);
            card1.setCardUid("UID123456"); // ⚡ Corregido el nombre
            card1.setIssuedAt(LocalDateTime.now());
            card1.setUser(user1);
            user1.setAccessCard(card1); // mantener la referencia bidireccional
            session.persist(card1);

            AccessCard card2 = new AccessCard();
            card2.setActive(true);
            card2.setCardUid("UID654321"); // ⚡ Corregido el nombre
            card2.setIssuedAt(LocalDateTime.now());
            card2.setUser(user2);
            user2.setAccessCard(card2);
            session.persist(card2);

            // 3️⃣ Crear Venues
            Venue venue1 = new Venue("Calle Mayor 1", "Madrid", LocalDateTime.now(), "Madrid Center");
            Venue venue2 = new Venue("Calle Luna 5", "Barcelona", LocalDateTime.now(), "Barcelona Hub");
            session.persist(venue1);
            session.persist(venue2);

            // 4️⃣ Crear Tags
            Tag tag1 = new Tag("Silencioso");
            Tag tag2 = new Tag("Luz Natural");
            session.persist(tag1);
            session.persist(tag2);

            // 5️⃣ Crear Spaces
            Space space1 = new Space(true, "S001", 4, new BigDecimal("15.50"), "Sala A", SpaceType.HABITACION);
            space1.setVenue(venue1);
            venue1.getSpaces().add(space1);
            space1.getTags().add(tag1);
            tag1.getSpaces().add(space1);
            session.persist(space1);

            Space space2 = new Space(true, "S002", 6, new BigDecimal("20.00"), "Sala B", SpaceType.ESCRITORIO);
            space2.setVenue(venue2);
            venue2.getSpaces().add(space2);
            space2.getTags().add(tag2);
            tag2.getSpaces().add(space2);
            session.persist(space2);

            // 6️⃣ Crear Bookings
            Booking booking1 = new Booking(
                    LocalDateTime.now(),
                    LocalDateTime.of(2025, 11, 12, 10, 0),
                    LocalDateTime.of(2025, 11, 12, 12, 0),
                    BookingStatus.CONFIRMADO,
                    31
            );
            booking1.setUser(user1);
            booking1.setSpace(space1);
            user1.getBookings().add(booking1);
            space1.getBookings().add(booking1);
            session.persist(booking1);

            Booking booking2 = new Booking(
                    LocalDateTime.now(),
                    LocalDateTime.of(2025, 11, 15, 9, 0),
                    LocalDateTime.of(2025, 11, 15, 11, 0),
                    BookingStatus.PENDIENTE,
                    40
            );
            booking2.setUser(user2);
            booking2.setSpace(space2);
            user2.getBookings().add(booking2);
            space2.getBookings().add(booking2);
            session.persist(booking2);

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
