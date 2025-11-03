package org.example.Actividad2;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String nickname;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}
