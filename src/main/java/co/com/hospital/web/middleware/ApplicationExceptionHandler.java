package co.com.hospital.web.middleware;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public abstract class ApplicationExceptionHandler<E extends Exception> extends ResponseEntityExceptionHandler {
    abstract protected ResponseEntity<Object> handleConflict(E exception, WebRequest request);

    protected void logStackTrace(E exception) {
        String stackTrace = ExceptionUtils.getStackTrace(exception);
        logger.error(stackTrace);
    }
}
