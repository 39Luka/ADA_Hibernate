package org.example.Actividad2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "rfidcard")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RfidCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String uid;

    @Column(nullable = false)
    private LocalDateTime issuedAt = LocalDateTime.now();

    @Getter
    @Column(nullable = false)
    private boolean active;

    @OneToOne
    @JoinColumn(name = "player_id", nullable = false, unique = true)
    private Player player;


}
