package org.example.Actividad2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "achievement")
@Getter
@Setter
@NoArgsConstructor
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // nombre del logro

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game; // juego al que pertenece este logro

    @ManyToMany(mappedBy = "achievements")
    private Set<Player> players = new HashSet<>();
}
