package co.com.hospital.utils;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
public class HttpException extends RuntimeException {
    private final String message;
    private final HttpStatus statusCode;

    public HttpException(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
