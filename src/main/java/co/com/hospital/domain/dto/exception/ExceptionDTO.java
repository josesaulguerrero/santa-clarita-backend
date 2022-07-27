package co.com.hospital.domain.dto.exception;

import co.com.hospital.utils.HttpException;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ExceptionDTO {
    private final String message;
    private final HttpStatus statusCode;

    public ExceptionDTO(HttpException exception) {
        this.message = exception.getMessage();
        this.statusCode = exception.getStatusCode();
    }

    public ExceptionDTO(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
