package ru.kataproject.p_sm_airlines_1.util.exceptions;

/**
 * Class DocumentNotFoundException.
 * Implements DocumentNotFound Exception.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 13.10.2022
 */
public class DocumentNotFoundException extends AbstractResourceNotFoundException {

    public DocumentNotFoundException() {}

    public DocumentNotFoundException(Long id) {
        super(String.valueOf(id));
    }

    @Override
    protected String getResourceAlias() {
        return "Document";
    }
}