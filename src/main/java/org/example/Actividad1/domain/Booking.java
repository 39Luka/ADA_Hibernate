package org.example.Actividad1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time",nullable = false)
    private LocalDateTime endTime;

    @Column(name="start_time",nullable = false, length = 10)
    private BookingStatus status;

    @Column(nullable = false)
    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Booking(LocalDateTime createdAt, LocalDateTime startTime, LocalDateTime endTime, BookingStatus status, Integer totalPrice) {
        this.createdAt = createdAt;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.totalPrice = totalPrice;
    }
}
