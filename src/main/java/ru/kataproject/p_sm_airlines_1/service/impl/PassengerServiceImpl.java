package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kataproject.p_sm_airlines_1.entity.Dto.PassengerDto;
import ru.kataproject.p_sm_airlines_1.entity.Passenger;
import ru.kataproject.p_sm_airlines_1.repository.PassengerRepository;
import ru.kataproject.p_sm_airlines_1.service.PassengerService;
import ru.kataproject.p_sm_airlines_1.util.exceptions.PassengerNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.PassengerMapper passengerMapper;

    @Override
    public List<PassengerDto> getAllPassengers() {
        return passengerRepository.findAll()
                .stream()
                .map(passengerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Passenger getPassengerById(Long id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));
    }

    @Override
    @Transactional
    public void updatePassenger(Passenger updatedPassenger) {
        updatedPassenger.setDocuments(getPassengerById(updatedPassenger.getId()).getDocuments());
        passengerRepository.save(updatedPassenger);
    }

    @Override
    @Transactional
    public void savePassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    @Override
    @Transactional
    public void deletePassengerById(Long id) {
        passengerRepository.delete(getPassengerById(id));
    }
}