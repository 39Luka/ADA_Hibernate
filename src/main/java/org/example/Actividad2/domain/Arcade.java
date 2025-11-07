package org.example.Actividad2.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "arcade")
public class Arcade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 200)
    private String address;

}

