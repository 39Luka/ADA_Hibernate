package org.example.Actividad2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "arcade")
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(
        name = "Arcade.byName",
        query = "select a from Arcade a where lower(a.name) = :name"
)
@ToString
public class Arcade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 200)
    private String address;

}

