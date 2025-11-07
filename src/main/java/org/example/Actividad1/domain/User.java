package org.example.Actividad1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
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
