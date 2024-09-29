package pl.hackyeah.hackyeah24_backend.responses;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ResponseManager {

    public static <T> ResponseEntity<T> ok(T body){
        final HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.ok().headers(headers).body(body);
    }
    public static <T> ResponseEntity created(T body){
        final HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(201).headers(headers).body(body);
    }
    public static <T> ResponseEntity updated(T body){
        final HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(234).headers(headers).body(body);
    }


    public static ResponseEntity ok(){
        final HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.ok().headers(headers).build();
    }

    public static ResponseEntity deleted(){
        final HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(204).headers(headers).build();
    }

    public static ResponseEntity created(){
        final HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(201).headers(headers).build();
    }
    public static <T> ResponseEntity updated(){
        final HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.status(234).headers(headers).build();
    }

}
