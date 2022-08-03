package co.com.hospital.web.middleware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ValidationExceptionHandler extends ApplicationExceptionHandler<MethodArgumentNotValidException> {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        return this.handleConflict(ex, request);
    }

    @Override
    protected ResponseEntity<Object> handleConflict(MethodArgumentNotValidException exception, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("devMessage", exception.getMessage());
        responseBody.put("stackTrace", Arrays.toString(exception.getStackTrace()));
        responseBody.put("statusCode", httpStatus.toString());
        super.logStackTrace(exception);

        return super.handleExceptionInternal(
                exception,
                responseBody,
                super.mapHeaders(request),
                httpStatus,
                request
        );
    }
}
