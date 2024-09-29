package pl.hackyeah.hackyeah24_backend.exceptions.rest;

public class RestAlreadyExistsException extends RestException{
    public RestAlreadyExistsException(String message) {
        super(message);
    }
}
