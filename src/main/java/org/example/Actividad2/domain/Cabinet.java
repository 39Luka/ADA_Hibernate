package org.example.Actividad2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "cabinet")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cabinet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String slug;

    private Integer buildYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column
    private Long hourly_cost;

    @ManyToOne
    @JoinColumn(name = "arcade_id", nullable = false)
    private Arcade arcade;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToMany
    @JoinTable(
            name = "cabinet_tag",
            joinColumns = @JoinColumn(name = "cabinet_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new java.util.HashSet<>();


}

