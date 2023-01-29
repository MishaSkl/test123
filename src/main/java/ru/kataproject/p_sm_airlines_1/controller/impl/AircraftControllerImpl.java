package ru.kataproject.p_sm_airlines_1.controller.impl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kataproject.p_sm_airlines_1.controller.AircraftController;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AircraftDto;
import ru.kataproject.p_sm_airlines_1.service.AircraftService;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.AircraftMapper;

import java.util.List;

@Log4j2
@RestController
@SecurityRequirement(name = "swagger_auth")
@RequiredArgsConstructor
public class AircraftControllerImpl implements AircraftController {
    private final AircraftService aircraftService;
    private final AircraftMapper aircraftMapper;

    @Override
    public ResponseEntity<List<AircraftDto>> getAllAircrafts() {
        log.info("execute getAllAircrafts method");
        return new ResponseEntity<>(aircraftService.getAllAircrafts(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AircraftDto> getAircraftById(Long id) {
        log.info("execute getAircraftById method");
        return new ResponseEntity<>(aircraftMapper.toDto(aircraftService.getAircraftById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> createAircraft(AircraftDto aircraft) {
        log.info("execute createAircraft method");
        aircraftService.saveAircraft(aircraftMapper.toModel(aircraft));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> updateAircraft(AircraftDto aircraftDto, Long id) {
        log.info("execute updateAircraft method");
        aircraftService.updateAircraft(aircraftMapper.toModel(aircraftDto).setId(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAircraftById(Long id) {
        log.info("execute deleteAircraftById method");
        aircraftService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}