package org.example.Actividad1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "spaces")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"bookings","tags"})
public class Space {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false, length = 60)
    private String code;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal hourlyPrice;

    @Column(nullable = false, length = 150)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SpaceType type;

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @ManyToMany
    @JoinTable(
            name = "space_tag",
            joinColumns = @JoinColumn(name = "space_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "space")
    private List<Booking> bookings = new ArrayList<>();

    public Space(boolean active, String code, Integer capacity, BigDecimal hourlyPrice, String name, SpaceType type) {
        this.active = active;
        this.code = code;
        this.capacity = capacity;
        this.hourlyPrice = hourlyPrice;
        this.name = name;
        this.type = type;
    }
}

