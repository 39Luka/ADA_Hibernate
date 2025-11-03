package org.example.Actividad1;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, length = 190, unique = true)
    private String email;

    @Column(nullable = false, length = 150)
    private String fullName;

    @OneToOne(mappedBy = "user")
    private AccessCard accessCard;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings = new ArrayList<>();
}
