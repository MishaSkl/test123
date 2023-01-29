package ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kataproject.p_sm_airlines_1.entity.Aircraft;
import ru.kataproject.p_sm_airlines_1.entity.Destination;
import ru.kataproject.p_sm_airlines_1.entity.Dto.AircraftDto;
import ru.kataproject.p_sm_airlines_1.entity.Dto.DestinationDTO;
import ru.kataproject.p_sm_airlines_1.entity.Dto.FlightDto;
import ru.kataproject.p_sm_airlines_1.entity.Flight;
import ru.kataproject.p_sm_airlines_1.entity.FlightStatus;
import ru.kataproject.p_sm_airlines_1.repository.DestinationRepository;
import ru.kataproject.p_sm_airlines_1.repository.FlightRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FlightMapperTest {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private FlightMapper flightMapper;

    private final Random r = new Random();

    private static FlightDto flightDtoExpected;
    private static Flight flightExpected;


    @BeforeEach
    void setUp() {
        Destination destination2 = new Destination();
        destination2
                .setCity("" + r.nextInt(1000))
                .setCountryCode("" + r.nextInt(1000))
                .setCountryName("" + r.nextInt(1000))
                .setAirportName("" + r.nextInt(1000))
                .setAirportCode("" + r.nextInt(1000))
                .setTimezone(r.nextInt(1000))
                .setRouteList(new ArrayList<>())
                .setRoutes(new ArrayList<>());
        destinationRepository.saveAndFlush(destination2);

        Destination destination1 = new Destination();
        destination1
                .setCity("" + r.nextInt(1000))
                .setCountryCode("" + r.nextInt(1000))
                .setCountryName("" + r.nextInt(1000))
                .setAirportName("" + r.nextInt(1000))
                .setAirportCode("" + r.nextInt(1000))
                .setTimezone(r.nextInt(1000))
                .setRouteList(new ArrayList<>())
                .setRoutes(new ArrayList<>());
        destinationRepository.saveAndFlush(destination1);

        Aircraft aircraft = new Aircraft();
        aircraft.setOnBoardNumber("board-" + r.nextInt(1000));
        aircraft.setStamp("stamp-" + r.nextInt(1000));
        aircraft.setModel("model-" + r.nextInt(1000));
        aircraft.setYearOfRelease(2022);

        flightExpected = new Flight();
        flightExpected
                .setDestinationFrom(destination1)
                .setDestinationTo(destination2)
                .setDepartureDateTime(LocalDateTime.now())
                .setArrivalDateTime(LocalDateTime.now())
                .setAircraft(aircraft)
                .setFlightStatus(FlightStatus.CANCELLED)
                .setInflightServices("" + r.nextInt(1000));

        flightRepository.saveAndFlush(flightExpected);

        DestinationDTO destinationDTO1 = new DestinationDTO(
                destination1.getId(),
                destination1.getCity(),
                destination1.getCountryCode(),
                destination1.getCountryName(),
                destination1.getAirportName(),
                destination1.getAirportCode(),
                destination1.getTimezone(),
                destination1.getRoutes(),
                destination1.getRouteList()

        );
        DestinationDTO destinationDTO2 = new DestinationDTO(
                destination2.getId(),
                destination2.getCity(),
                destination2.getCountryCode(),
                destination2.getCountryName(),
                destination2.getAirportName(),
                destination2.getAirportCode(),
                destination2.getTimezone(),
                destination2.getRoutes(),
                destination2.getRouteList()
        );

        AircraftDto aircraftDto = new AircraftDto(
                aircraft.getId(),
                aircraft.getOnBoardNumber(),
                aircraft.getStamp(),
                aircraft.getModel(),
                aircraft.getYearOfRelease()
        );

        flightDtoExpected = new FlightDto(
                flightExpected.getId(),
                destinationDTO1,
                destinationDTO2,
                flightExpected.getDepartureDateTime(),
                flightExpected.getArrivalDateTime(),
                aircraftDto,
                flightExpected.getFlightStatus(),
                flightExpected.getInflightServices()
        );
    }

    @Test
    void shouldMapToModel() {
        // given

        //  when
        Flight flightActual = flightMapper.toModel(flightDtoExpected);

        // then
        assertThat(flightActual.getId()).isEqualTo(flightExpected.getId());
        assertThat(flightActual.getFlightStatus()).isEqualTo(flightExpected.getFlightStatus());
        assertThat(flightActual.getInflightServices()).isEqualTo(flightExpected.getInflightServices());
        assertThat(flightActual.getArrivalDateTime()).isEqualTo(flightExpected.getArrivalDateTime());
        assertThat(flightActual.getDepartureDateTime()).isEqualTo(flightExpected.getDepartureDateTime());
        assertThat(flightActual.getAircraft()).isEqualTo(flightExpected.getAircraft());

        assertThat(flightActual.getDestinationFrom().getId()).isEqualTo(flightExpected.getDestinationFrom().getId());
        assertThat(flightActual.getDestinationFrom().getCity()).isEqualTo(flightExpected.getDestinationFrom().getCity());
        assertThat(flightActual.getDestinationFrom().getAirportCode()).isEqualTo(flightExpected.getDestinationFrom().getAirportCode());
        assertThat(flightActual.getDestinationFrom().getCountryCode()).isEqualTo(flightExpected.getDestinationFrom().getCountryCode());
        assertThat(flightActual.getDestinationFrom().getTimezone()).isEqualTo(flightExpected.getDestinationFrom().getTimezone());
        assertThat(flightActual.getDestinationFrom().getAirportName()).isEqualTo(flightExpected.getDestinationFrom().getAirportName());
        assertThat(flightActual.getDestinationFrom().getRoutes()).isEqualTo(flightExpected.getDestinationFrom().getRoutes());
        assertThat(flightActual.getDestinationFrom().getRouteList()).isEqualTo(flightExpected.getDestinationFrom().getRouteList());

        assertThat(flightActual.getDestinationTo().getId()).isEqualTo(flightExpected.getDestinationTo().getId());
        assertThat(flightActual.getDestinationTo().getCity()).isEqualTo(flightExpected.getDestinationTo().getCity());
        assertThat(flightActual.getDestinationTo().getAirportCode()).isEqualTo(flightExpected.getDestinationTo().getAirportCode());
        assertThat(flightActual.getDestinationTo().getCountryCode()).isEqualTo(flightExpected.getDestinationTo().getCountryCode());
        assertThat(flightActual.getDestinationTo().getTimezone()).isEqualTo(flightExpected.getDestinationTo().getTimezone());
        assertThat(flightActual.getDestinationTo().getAirportName()).isEqualTo(flightExpected.getDestinationTo().getAirportName());
        assertThat(flightActual.getDestinationTo().getRoutes()).isEqualTo(flightExpected.getDestinationTo().getRoutes());
        assertThat(flightActual.getDestinationTo().getRouteList()).isEqualTo(flightExpected.getDestinationTo().getRouteList());
    }

    @Test
    void shouldMapToDto() {
        // given

        // when
        FlightDto flightDtoActual = flightMapper.toDto(flightExpected);

        // then
        assertThat(flightDtoActual.getId()).isEqualTo(flightDtoExpected.getId());
        assertThat(flightDtoActual.getFlightStatus()).isEqualTo(flightDtoExpected.getFlightStatus());
        assertThat(flightDtoActual.getAircraft()).isEqualTo(flightDtoExpected.getAircraft());
        assertThat(flightDtoActual.getArrivalDateTime()).isEqualTo(flightDtoExpected.getArrivalDateTime());
        assertThat(flightDtoActual.getDepartureDateTime()).isEqualTo(flightDtoExpected.getDepartureDateTime());
        assertThat(flightDtoActual.getInflightServices()).isEqualTo(flightDtoExpected.getInflightServices());

        assertThat(flightDtoActual.getDestinationFrom().getId()).isEqualTo(flightDtoExpected.getDestinationFrom().getId());
        assertThat(flightDtoActual.getDestinationFrom().getCity()).isEqualTo(flightDtoExpected.getDestinationFrom().getCity());
        assertThat(flightDtoActual.getDestinationFrom().getAirportCode()).isEqualTo(flightDtoExpected.getDestinationFrom().getAirportCode());
        assertThat(flightDtoActual.getDestinationFrom().getCountryCode()).isEqualTo(flightDtoExpected.getDestinationFrom().getCountryCode());
        assertThat(flightDtoActual.getDestinationFrom().getTimezone()).isEqualTo(flightDtoExpected.getDestinationFrom().getTimezone());
        assertThat(flightDtoActual.getDestinationFrom().getAirportName()).isEqualTo(flightDtoExpected.getDestinationFrom().getAirportName());
        assertThat(flightDtoActual.getDestinationFrom().getRoutes()).isEqualTo(flightDtoExpected.getDestinationFrom().getRoutes());
        assertThat(flightDtoActual.getDestinationFrom().getRouteList()).isEqualTo(flightDtoExpected.getDestinationFrom().getRouteList());

        assertThat(flightDtoActual.getDestinationTo().getId()).isEqualTo(flightDtoExpected.getDestinationTo().getId());
        assertThat(flightDtoActual.getDestinationTo().getCity()).isEqualTo(flightDtoExpected.getDestinationTo().getCity());
        assertThat(flightDtoActual.getDestinationTo().getAirportCode()).isEqualTo(flightDtoExpected.getDestinationTo().getAirportCode());
        assertThat(flightDtoActual.getDestinationTo().getCountryCode()).isEqualTo(flightDtoExpected.getDestinationTo().getCountryCode());
        assertThat(flightDtoActual.getDestinationTo().getTimezone()).isEqualTo(flightDtoExpected.getDestinationTo().getTimezone());
        assertThat(flightDtoActual.getDestinationTo().getAirportName()).isEqualTo(flightDtoExpected.getDestinationTo().getAirportName());
        assertThat(flightDtoActual.getDestinationTo().getRoutes()).isEqualTo(flightDtoExpected.getDestinationTo().getRoutes());
        assertThat(flightDtoActual.getDestinationTo().getRouteList()).isEqualTo(flightDtoExpected.getDestinationTo().getRouteList());
    }
}
