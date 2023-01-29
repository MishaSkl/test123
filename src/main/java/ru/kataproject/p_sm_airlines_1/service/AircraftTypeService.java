package ru.kataproject.p_sm_airlines_1.service;

import ru.kataproject.p_sm_airlines_1.entity.AircraftType;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AircraftTypeDto;

import java.util.List;

/**
 * Interface AircraftType.
 * Declares AircraftType Service API.
 *
 * @author Kosarev Oleg
 * @since 12.01.2023
 */

public interface AircraftTypeService {
    List<AircraftTypeDto> getAllAircraftTypes();

    AircraftType getAircraftTypeById(Long id);

    void updateAircraftType(AircraftType aircraftType);

    void saveAircraftType(AircraftType aircraftType);

    void deleteAircraftTypeById(Long id);
}
