package org.example.Actividad1;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")

public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Space> spaces = new HashSet<>();
}
