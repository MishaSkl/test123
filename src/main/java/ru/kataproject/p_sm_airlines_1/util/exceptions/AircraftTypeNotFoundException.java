package ru.kataproject.p_sm_airlines_1.util.exceptions;

/**
 * Implements AircraftTypeNotFound Exception.
 *
 * @author Kosarev Oleg
 * @since 12.01.2023
 */

public class AircraftTypeNotFoundException extends AbstractResourceNotFoundException {
    public AircraftTypeNotFoundException(Long id) {
        super(String.valueOf(id));
    }

    @Override
    protected String getResourceAlias() {
        return "AircraftType";
    }
}
