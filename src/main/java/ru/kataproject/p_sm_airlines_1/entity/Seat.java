package ru.kataproject.p_sm_airlines_1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Objects;

/**
 * The Seat class represents a seat in an aircraft cabin.
 *
 * @author Romanov Leonid (romanovsparta@ya.ru)
 * @since 07.10.2022
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Seat {
    @Id
    @GeneratedValue
    private Long id;

    private String seatNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seat_type", nullable = false, referencedColumnName = "id")
    private SeatType seatType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aircraft", nullable = false, referencedColumnName = "id")
    private Aircraft aircraft;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(id, seat.id) && Objects.equals(seatNumber, seat.seatNumber)
                && Objects.equals(seatType, seat.seatType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seatNumber, seatType);
    }
}