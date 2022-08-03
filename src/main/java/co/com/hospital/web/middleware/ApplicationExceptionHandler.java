package co.com.hospital.web.middleware;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

public abstract class ApplicationExceptionHandler<E extends Exception> extends ResponseEntityExceptionHandler {
    abstract protected ResponseEntity<Object> handleConflict(E exception, WebRequest request);

    protected HttpHeaders mapHeaders(WebRequest request) {
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

    protected void logStackTrace(E exception) {
        String stackTrace = ExceptionUtils.getStackTrace(exception);
        logger.error(stackTrace);
    }
}
