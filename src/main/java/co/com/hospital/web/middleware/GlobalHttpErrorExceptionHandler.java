package co.com.hospital.web.middleware;

import co.com.hospital.utils.HttpException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHttpErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {HttpException.class})
    protected ResponseEntity<Object> handleConflict(HttpException exception, WebRequest request) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("statusCode", exception.getStatusCode().toString());
        responseBody.put("message", exception.getMessage());
        responseBody.put("stackTrace", Arrays.toString(exception.getStackTrace()));
        return handleExceptionInternal(
                exception,
                responseBody,
                new HttpHeaders(),
                exception.getStatusCode(),
                request
        );
    }
}
