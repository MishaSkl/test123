package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kataproject.p_sm_airlines_1.entity.Dto.SeatDto;
import ru.kataproject.p_sm_airlines_1.entity.Seat;
import ru.kataproject.p_sm_airlines_1.repository.AircraftRepository;
import ru.kataproject.p_sm_airlines_1.repository.SeatRepository;
import ru.kataproject.p_sm_airlines_1.repository.SeatTypeRepository;
import ru.kataproject.p_sm_airlines_1.service.SeatService;
import ru.kataproject.p_sm_airlines_1.util.exceptions.AircraftNotFoundException;
import ru.kataproject.p_sm_airlines_1.util.exceptions.SeatNotFoundException;
import ru.kataproject.p_sm_airlines_1.util.exceptions.SeatTypeNotFoundException;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.SeatMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements SeatService interface.
 * Changed by:
 *
 * @author Alexey Sen (alexey.sen@gmail.com)
 * @since 01.11.2022
 */
@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final AircraftRepository aircraftRepository;
    private final SeatTypeRepository seatTypeRepository;
    private SeatMapper seatMapper;
    @Autowired
    public void setSeatMapper(SeatMapper seatMapper) {
        this.seatMapper = seatMapper;
    }

    @Override
    public List<SeatDto> getAllSeats() {
        return seatRepository.findAll()
                .stream()
                .map(seatMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Seat getSeatById(Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new SeatNotFoundException(id));
    }

    @Override
    @Transactional
    public void saveSeat(Seat seat) {
        // Маппер, используя id aircraft и seatType в seatDto,
        // уже вызвал методы getById у них => проверок делать не нужно
        seat.getAircraft().addSeat(seat);
        seat.getSeatType().addSeat(seat);
        seatRepository.saveAndFlush(seat);
    }

    @Override
    @Transactional
    public void updateSeat(Seat seat) {
        // Вывести исключение, если id не существует в базе
        if (!seatRepository.existsById(seat.getId())) {
            throw new SeatNotFoundException(seat.getId());
        }
        seatRepository.saveAndFlush(seat);
    }

    @Override
    @Transactional
    public void deleteSeat(Long id) {
        Seat seat = getSeatById(id);
        seat.getAircraft().removeSeat(seat);
        seat.getSeatType().removeSeat(seat);
    }

    @Override
    public List<Seat> getSeatsByAircraftId(Long aircraftId) {
        // Вывести исключение, если id aircraft-a не существует в базе
        if (!aircraftRepository.existsById(aircraftId)) {
            throw new AircraftNotFoundException(aircraftId);
        }

        return seatRepository.getSeatsByAircraft_Id(aircraftId);
    }

    @Override
    public List<Seat> getSeatByAircraftAndSeatTypeId(Long aircraftId, Long seatTypeId) {
        // Вывести исключение, если id aircraft-a не существует в базе
        if (!aircraftRepository.existsById(aircraftId)) {
            throw new AircraftNotFoundException(aircraftId);
        }
        // Вывести исключение, если id seatType-a не существует в базе
        if (!seatTypeRepository.existsById(seatTypeId)) {
            throw new SeatTypeNotFoundException(seatTypeId);
        }
        return seatRepository.getSeatsByAircraft_IdAndSeatType_Id(aircraftId, seatTypeId);
    }
}
