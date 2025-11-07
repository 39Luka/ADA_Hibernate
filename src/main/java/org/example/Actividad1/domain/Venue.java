package org.example.Actividad1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venues")
@Setter
@Getter
@NoArgsConstructor
public class Venue {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 250)
    private String address;

    @Column(nullable = false, length = 120)
    private String city;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, length = 60)
    private String name;

    @OneToMany(mappedBy = "venue")
    private List<Space> spaces = new ArrayList<>();
}
