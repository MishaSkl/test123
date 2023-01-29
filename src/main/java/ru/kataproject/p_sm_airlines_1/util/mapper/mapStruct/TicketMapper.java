package ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kataproject.p_sm_airlines_1.entity.Dto.TicketDto;
import ru.kataproject.p_sm_airlines_1.entity.Passenger;
import ru.kataproject.p_sm_airlines_1.entity.Ticket;
import ru.kataproject.p_sm_airlines_1.service.FlightService;
import ru.kataproject.p_sm_airlines_1.service.PassengerService;
import ru.kataproject.p_sm_airlines_1.service.SeatService;


@Mapper(componentModel = "spring", uses = {SeatService.class, PassengerService.class, FlightService.class})
public interface TicketMapper {

    /**
     * Method maps TicketDto to Ticket
     * @param ticketDto TicketDto
     * @return Ticket
     */
    @Mapping(source = "seat", target = "seat")
    @Mapping(source = "passenger", target = "passenger")
    @Mapping(source = "flight", target = "flight")
    Ticket toModel(TicketDto ticketDto);

    /**
     * Method maps Ticket to TicketDto
     * @param ticket Ticket
     * @return TicketDto
     */
    @Mapping(source = "seat.id", target = "seat")
    @Mapping(source = "passenger.id", target = "passenger")
    @Mapping(source = "flight.id", target = "flight")
    TicketDto toDto(Ticket ticket);

}
