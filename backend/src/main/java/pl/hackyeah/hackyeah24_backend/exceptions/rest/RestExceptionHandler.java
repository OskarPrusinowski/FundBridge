package pl.hackyeah.hackyeah24_backend.exceptions.rest;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import pl.hackyeah.hackyeah24_backend.responses.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {RestNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleException(RestNotFoundException exc){
        return handleException(exc, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {RestException.class})
    public ResponseEntity<ErrorResponse> handleException(RestException exc){
        return handleException(exc,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorResponse> handleException(RestException exc,HttpStatus status){
        ErrorResponse error = new ErrorResponse();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        error.setStatus(status);
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(status).headers(headers).body(error);
    }
}
