package pl.hackyeah.hackyeah24_backend.exceptions.rest;

public class RestException extends RuntimeException{
    public RestException(String message) {
        super(message);
    }
}
