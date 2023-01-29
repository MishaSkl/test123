package ru.kataproject.p_sm_airlines_1.util.exceptions;

public class SeatTypeNotFoundException extends AbstractResourceNotFoundException {
    public SeatTypeNotFoundException(Long id) {
        super(String.valueOf(id));
    }

    @Override
    protected String getResourceAlias() {
        return "SeatType";
    }
}
