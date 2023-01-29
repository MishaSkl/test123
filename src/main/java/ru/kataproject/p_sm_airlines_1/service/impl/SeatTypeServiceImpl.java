package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kataproject.p_sm_airlines_1.entity.Dto.SeatTypeDTO;
import ru.kataproject.p_sm_airlines_1.entity.Seat;
import ru.kataproject.p_sm_airlines_1.entity.SeatType;
import ru.kataproject.p_sm_airlines_1.repository.AircraftRepository;
import ru.kataproject.p_sm_airlines_1.repository.SeatTypeRepository;
import ru.kataproject.p_sm_airlines_1.service.SeatService;
import ru.kataproject.p_sm_airlines_1.service.SeatTypeService;
import ru.kataproject.p_sm_airlines_1.util.exceptions.SeatTypeNotFoundException;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.SeatTypeMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatTypeServiceImpl implements SeatTypeService {

    private final SeatTypeRepository seatTypeRepository;
    private final SeatTypeMapper seatTypeMapper;
    private SeatService seatService;
    @Autowired
    public void setSeatService(SeatService seatService) { this.seatService = seatService; }

    @Override
    public List<SeatTypeDTO> getAllSeatTypes() {
        return seatTypeRepository.findAll()
                .stream()
                .map(seatTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SeatType getSeatTypeById(Long id) {
        return seatTypeRepository.findById(id)
                .orElseThrow(() -> new SeatTypeNotFoundException(id));
    }

    @Override
    public void saveSeatType(SeatType seatType) {
        seatTypeRepository.save(seatType);
    }

    @Override
    public void updateSeatType(SeatType seatType) {
        // Вывести исключение, если id не существует в базе
        seatType.setSeats(getSeatTypeById(seatType.getId()).getSeats());
        seatTypeRepository.save(seatType);
    }

    @Override
    public void deleteSeatType(Long id) {
        seatTypeRepository.delete(getSeatTypeById(id));
    }

    @Override
    public List<SeatType> getAllSeatTypesInAircraft(Long aircraftId) {
        return seatService.getSeatsByAircraftId(aircraftId)
                .stream()
                .map(Seat::getSeatType)
                .distinct()
                .collect(Collectors.toList());
    }
}
