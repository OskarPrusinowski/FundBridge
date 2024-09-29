package pl.hackyeah.hackyeah24_backend.exceptions.rest;

public class RestNotFoundException extends RestException{
    public RestNotFoundException(String message) {
        super(message);
    }

    public RestNotFoundException(int id) {
        this("Nie znalezion o id: %s".formatted(id));
    }
}
