package ru.kataproject.p_sm_airlines_1.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;


/**
 * Сущность AircraftType
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "aircraft_type")
public class AircraftType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iata_code", nullable = false, length = 3) // Тип самолета https://www.yestravel.ru/skyticket/aircraft-types/
    private String iataCode;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "range")
    private int range;
}
