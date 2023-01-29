package ru.kataproject.p_sm_airlines_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AircraftTypeDto;

import javax.validation.Valid;
import java.util.List;

/**
 * Interface DocumentController.
 * Declares Document API methods.
 *
 * @author Kosarev Oleg
 * @since 12.01.2023
 */

@Tag(name = "AircraftType Controller", description = "Operations with aircraft types")
@RequestMapping(AircraftTypeController.BASE_NAME)
public interface AircraftTypeController {
    String MAJOR_VERSION = "/v1";
    String BASE_NAME = MAJOR_VERSION + "/aircraft_types";

    /**
     * Method returns all aircraft types.
     */
    @GetMapping
    @Operation(summary = "Get list of all aircraft types")
    @ApiResponse(responseCode = "200", description = "Page successfully returned")
    ResponseEntity<List<AircraftTypeDto>> getAllAircraftTypes();

    /**
     * Method returns aircraft type by id.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get aircraft type by id")
    @ApiResponse(responseCode = "200", description = "Page successfully returned")
    @ApiResponse(responseCode = "404", description = "Page not found")
    ResponseEntity<AircraftTypeDto> getAircraftTypeById(@PathVariable Long id);

    /**
     * Method creates new aircraft type.
     */
    @PostMapping
    @Operation(summary = "Create new aircraft type")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Page successfully returned")
    @ApiResponse(responseCode = "405", description = "Method Not Allowed")
    ResponseEntity<AircraftTypeDto> createAircraftType(@Valid @RequestBody AircraftTypeDto aircraftTypeDto);

    /**
     * Method updates aircraft type by id.
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Update aircraft type")
    @ApiResponse(responseCode = "200", description = "Aircraft type successfully updated")
    @ApiResponse(responseCode = "404", description = "Page not found")
    ResponseEntity<AircraftTypeDto> updateAircraftType(@Valid @RequestBody AircraftTypeDto aircraftTypeDto,
                                                 @PathVariable Long id);
    /**
     * Method deletes aircraft type by id.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete aircraft type")
    @ApiResponse(responseCode = "204", description = "Aircraft type successfully removed")
    @ApiResponse(responseCode = "404", description = "Page not found")
    ResponseEntity<Void> deleteAircraftTypeById(@PathVariable Long id);

}
