package org.example.Actividad1;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
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

}
enum BookingStatus { PENDIENTE, CONFIRMADO, CANCELADO }
