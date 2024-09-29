package pl.hackyeah.hackyeah24_backend.exceptions;


import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.hackyeah.hackyeah24_backend.responses.ErrorResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        Map<String, List<String>> body = new HashMap<>();

        HttpHeaders customHeaders= new HttpHeaders();
        customHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        response.setMessages(errors);
        if(errors.size()>0){
            response.setMessage(errors.get(0));
        }
        response.setStatus(HttpStatus.valueOf(status.value()));
        response.setTimestamp(System.currentTimeMillis());



        return ResponseEntity.status(status).headers(customHeaders).body(response);
    }


}