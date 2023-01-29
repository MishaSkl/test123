package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kataproject.p_sm_airlines_1.entity.AircraftType;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AircraftTypeDto;
import ru.kataproject.p_sm_airlines_1.repository.AircraftTypeRepository;
import ru.kataproject.p_sm_airlines_1.service.AircraftTypeService;
import ru.kataproject.p_sm_airlines_1.util.exceptions.AircraftTypeNotFoundException;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.AircraftTypeMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class AircraftTypeServiceImpl.
 * Implements AircraftTypeService interface.
 *
 * @author Kosarev Oleg
 * @since 12.01.2023
 */

@Service
@RequiredArgsConstructor
public class AircraftTypeServiceImpl implements AircraftTypeService {
    private final AircraftTypeRepository aircraftTypeRepository;
    private final AircraftTypeMapper aircraftTypeMapper;

    @Override
    public List<AircraftTypeDto> getAllAircraftTypes() {
        return aircraftTypeRepository.findAll()
                .stream()
                .map(aircraftTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AircraftType getAircraftTypeById(Long id) {
        return aircraftTypeRepository.findById(id)
                .orElseThrow(() -> new AircraftTypeNotFoundException(id));
    }

    @Override
    @Transactional
    public void updateAircraftType(AircraftType aircraftType) {
        // Вывести исключение, если id не существует в базе
        if (!aircraftTypeRepository.existsById(aircraftType.getId())) {
            throw new AircraftTypeNotFoundException(aircraftType.getId());
        }

        aircraftTypeRepository.save(aircraftType);
    }

    @Override
    @Transactional
    public void saveAircraftType(AircraftType aircraftType) {
        aircraftTypeRepository.save(aircraftType);
    }

    @Override
    @Transactional
    public void deleteAircraftTypeById(Long id) {
        aircraftTypeRepository.delete(getAircraftTypeById(id));
    }

}
