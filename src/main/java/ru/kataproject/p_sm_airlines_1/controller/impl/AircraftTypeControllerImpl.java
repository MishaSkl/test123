package ru.kataproject.p_sm_airlines_1.controller.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kataproject.p_sm_airlines_1.controller.AircraftTypeController;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AircraftTypeDto;
import ru.kataproject.p_sm_airlines_1.service.AircraftTypeService;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.AircraftTypeMapper;

import java.util.List;

/**
 * Class AircraftTypeControllerImpl.
 * Implements AircraftTypeController interface.
 *
 * @author Kosarev Oleg
 * @since 12.01.2023
 */

@Log4j2
@RestController
@SecurityRequirement(name = "swagger_auth")
@AllArgsConstructor
public class AircraftTypeControllerImpl implements AircraftTypeController {

    private final AircraftTypeMapper aircraftTypeMapper;
    private final AircraftTypeService aircraftTypeService;

    @Override
    public ResponseEntity<List<AircraftTypeDto>> getAllAircraftTypes() {
        log.info("execute getAllAircraftTypes method");
        return new ResponseEntity<>(aircraftTypeService.getAllAircraftTypes(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AircraftTypeDto> getAircraftTypeById(Long id) {
        log.info("execute getAircraftTypeById method");
        return new ResponseEntity<>(aircraftTypeMapper.toDto(aircraftTypeService.getAircraftTypeById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AircraftTypeDto> createAircraftType(AircraftTypeDto aircraftTypeDto) {
        log.info("execute createAircraftType method");
        aircraftTypeService.saveAircraftType(aircraftTypeMapper.toModel(aircraftTypeDto));
        return new ResponseEntity<>(aircraftTypeDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AircraftTypeDto> updateAircraftType(AircraftTypeDto aircraftTypeDto, Long id) {
        log.info("execute updateAircraftType method");
        aircraftTypeService.updateAircraftType(aircraftTypeMapper.toModel(aircraftTypeDto).setId(id));
        return new ResponseEntity<>(aircraftTypeDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteAircraftTypeById(Long id) {
        log.info("execute deleteAircraftTypeById method");
        aircraftTypeService.deleteAircraftTypeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
