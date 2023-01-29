package ru.kataproject.p_sm_airlines_1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class SeatType {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private SeatCategory category;

    private Boolean hasWindow;

    private Boolean hasAdditPlace;

    private Boolean hasTv;

    private Boolean isAisle;

    @OneToMany(mappedBy = "seatType", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Seat> seats;

    public void addSeat(Seat seat) {
        this.seats.add(seat);
        seat.setSeatType(this);
    }
    public void removeSeat(Seat seat) {
        this.seats.remove(seat);
        seat.setSeatType(null);
    }
}