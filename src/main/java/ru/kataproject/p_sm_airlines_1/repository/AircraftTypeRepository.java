package ru.kataproject.p_sm_airlines_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kataproject.p_sm_airlines_1.entity.AircraftType;

/**
 * Interface AircraftTypeRepository.
 * Implements AircraftType DAO via Spring Data JPA.
 *
 * @author Kosarev Oleg
 * @since 12.01.2023
 */

public interface AircraftTypeRepository extends JpaRepository<AircraftType, Long> {
}
