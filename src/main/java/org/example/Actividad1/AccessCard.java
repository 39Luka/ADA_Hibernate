package org.example.Actividad1;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "access_card")
public class AccessCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false, length = 64)
    private String cardUid;

    @Column(nullable = false)
    private LocalDateTime issuedAt;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;
}
