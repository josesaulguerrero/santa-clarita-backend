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
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@ControllerAdvice
public class GlobalHttpErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {HttpException.class})
    protected ResponseEntity<Object> handleConflict(HttpException exception, WebRequest request) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("statusCode", exception.getStatusCode().toString());
        responseBody.put("devMessage", exception.getMessage());
        return handleExceptionInternal(
                exception,
                responseBody,
                this.mapHeaders(request),
                exception.getStatusCode(),
                request
        );
    }

    private HttpHeaders mapHeaders(WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        Iterable<String> headerNamesIterable = request::getHeaderNames;
        List<String> headersNamesList = StreamSupport
                .stream(headerNamesIterable.spliterator(), false)
                .toList();
        headersNamesList
                .forEach(name ->
                        headers.put(
                                name,
                                Arrays.asList(request.getHeaderValues(name))
                        ));
        headers.put("Content-Type", List.of("application/json"));
        return headers;
    }
}
