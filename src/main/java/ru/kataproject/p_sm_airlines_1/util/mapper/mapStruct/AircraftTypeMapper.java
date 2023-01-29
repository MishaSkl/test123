package ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct;

import org.mapstruct.Mapper;
import ru.kataproject.p_sm_airlines_1.entity.AircraftType;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AircraftTypeDto;

/**
 * Interface AircraftTypeMapper.
 * Declares Aircraft type mapper between AircraftType and AircraftTypeDto.
 *
 * @author Oleg Kosarev
 * @since 12.01.2023
 */

@Mapper(componentModel = "spring", uses = AircraftTypeMapper.class)
public interface AircraftTypeMapper {
    /**
     * Maps AircraftType to AircraftTypeDto
     * @param aircraftType AircraftType
     * @return PassengerDto
     */
    AircraftTypeDto toDto(AircraftType aircraftType);

    /**
     * Maps AircraftTypeDto to AircraftType
     * @param aircraftTypeDto AircraftTypeDto
     * @return AircraftTypeDto
     */
    AircraftType toModel(AircraftTypeDto aircraftTypeDto);
}
