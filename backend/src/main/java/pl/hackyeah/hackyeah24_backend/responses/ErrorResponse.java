package pl.hackyeah.hackyeah24_backend.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public class ErrorResponse {
    public void setStatus(HttpStatusCode status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private HttpStatusCode status;
    private String message;
    private List<String> messages;
    private long timestamp;

    public ErrorResponse() {
    }

    public ErrorResponse(HttpStatus status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }


    public ErrorResponse(HttpStatus status, List<String> messages, long timestamp) {
        this.status = status;
        this.messages = messages;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status.value();
    }
}
