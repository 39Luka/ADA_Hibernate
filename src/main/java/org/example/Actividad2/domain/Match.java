package org.example.Actividad2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startedAt = LocalDateTime.now();

    private Integer durationSec;
    private Integer score;

    @Enumerated(EnumType.STRING)
    private Result result;

    @Column(nullable = false)
    private Integer creditsUsed;

    @Column
    private LocalDateTime endAt;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "cabinet_id", nullable = false)
    private Cabinet cabinet;

}
