package org.example.Actividad1;

import org.example.Actividad1.domain.*;
import org.example.Actividad1.service.*;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil1.getSessionFactory();

        VenueService venueService = new VenueService(sf);
        Venue venue = new Venue("calle 1","madrid", LocalDateTime.now(),"sede1");

        Long venueId = venueService.create(venue);
        System.out.println("Venue: " +venueId);

        SpaceService spaceService = new SpaceService(sf);
        Space space = new Space(true, "SPACE1",5,new BigDecimal(9), "oficina1", SpaceType.OFICINA);

        Long spaceId = spaceService.createService(venueId,space);
        System.out.println("Space: " +spaceId);

        UserService userService = new UserService(sf);
        User user = new User(LocalDateTime.now(),"user1", "user.user@gmail.com");

        Long userId = userService.create(user);
        System.out.println("User:" + userId);

        Long accessCardId= userService.assignAccessCard(userId, "CARD1");
        System.out.println("AccessCard: "+ accessCardId);

        BookingService bookingService = new BookingService(sf);
        Long bookingId = bookingService.create(userId, spaceId,LocalDateTime.now(),LocalDateTime.now().plusDays(10),8,BookingStatus.PENDIENTE);
        System.out.println("Booking: "+bookingId );

        TagService tagService = new TagService(sf);
        Tag tag = new Tag("bonito");
        Long tagId = tagService.create(tag);
        System.out.println("Tag: "+tagId);
        tagService.addTagToSpace(spaceId,tagId);


        bookingService.deleteById(bookingId);
        userService.removeAccessCard(userId);
        userService.deleteById(userId);
        spaceService.deleteById(spaceId);
        tagService.deleteById(tagId);
        venueService.deleteById(venueId);


    }
}
