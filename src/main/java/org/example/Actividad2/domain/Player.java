package org.example.Actividad2.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "player")
@Getter
@Setter
@NoArgsConstructor
@ToString
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

    @OneToOne(mappedBy = "player") // 'player' es el campo de RfidCard que es el propietario
    private RfidCard rfidCard;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private Set<Match> matches = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "player_achievement",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id")
    )
    private Set<Achievement> achievements = new HashSet<>();
}
