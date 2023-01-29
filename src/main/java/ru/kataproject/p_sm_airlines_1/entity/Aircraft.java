package ru.kataproject.p_sm_airlines_1.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Сущность Aircraft
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "aircraft")
@ToString
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "on_board_number")
    private String onBoardNumber;  // бортовой номер

    @Column(name = "stamp")
    private String stamp; // марка

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year_of_release")
    private int yearOfRelease; // год выпуска

    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Seat> seats;

    public void addSeat(Seat seat) {
        this.seats.add(seat);
        seat.setAircraft(this);
    }
    public void removeSeat(Seat seat) {
        this.seats.remove(seat);
        seat.setAircraft(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aircraft aircraft = (Aircraft) o;
        return id == aircraft.id && Objects.equals(onBoardNumber, aircraft.onBoardNumber)
                && Objects.equals(stamp, aircraft.stamp) && Objects.equals(model, aircraft.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, onBoardNumber, stamp, model);
    }
}