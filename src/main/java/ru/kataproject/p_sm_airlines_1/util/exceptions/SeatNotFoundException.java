package ru.kataproject.p_sm_airlines_1.util.exceptions;

/**
 * Implements SeatNotFound Exception.
 *
 * @author Romanov Leonid (romanovsparta@ya.ru)
 * @since 14.10.2022
 */
public class SeatNotFoundException extends AbstractResourceNotFoundException {

    public SeatNotFoundException(Long id) {
        super(String.valueOf(id));
    }

    @Override
    protected String getResourceAlias() {
        return "Seat";
    }

}
