package ru.kataproject.p_sm_airlines_1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kataproject.p_sm_airlines_1.util.RefNumber;

import javax.persistence.*;

/**
 * The Ticket class represents a ticket on a flight.
 *
 * @author Romanov Leonid (romanovsparta@ya.ru)
 * @since 27.10.2022
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;
    @Enumerated(EnumType.STRING)
    private SeatCategory seatCategory;
    @OneToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;

    private String bookingNumber;

    public Ticket(Long id, Passenger passenger, Flight flight, SeatCategory seatCategory, Seat seat, String bookingNumber) {
        this.id = id;
        this.passenger = passenger;
        this.flight = flight;
        this.seatCategory = seatCategory;
        this.seat = seat;
        this.bookingNumber = RefNumber.generate();;
    }


}