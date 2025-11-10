package org.example.Actividad1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = "spaces")
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Space> spaces = new HashSet<>();

    public Tag(String name) {
        this.name = name;
    }
}
