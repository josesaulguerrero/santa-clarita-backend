package co.com.hospital.web.middleware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Priority;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
@Priority(100)
public class ValidationExceptionHandler extends ApplicationExceptionHandler<MethodArgumentNotValidException> {
    @Override
    @ResponseBody
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
        String errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        responseBody.put("devMessage", errors);
        responseBody.put("stackTrace", Arrays.toString(exception.getStackTrace()));
        responseBody.put("statusCode", httpStatus.toString());
        super.logStackTrace(exception);

        return ResponseEntity
                .badRequest()
                .headers(super.mapHeaders(request))
                .body(responseBody);
    }
}
