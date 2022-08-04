package co.com.hospital.web.middleware;

import co.com.hospital.utils.HttpException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class HttpExceptionHandler extends ApplicationExceptionHandler<HttpException> {
    @ExceptionHandler(value = {HttpException.class})
    protected ResponseEntity<Object> handleConflict(HttpException exception, WebRequest request) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("statusCode", exception.getStatusCode().toString());
        responseBody.put("devMessage", exception.getMessage());
        responseBody.put("stackTrace", ExceptionUtils.getStackTrace(exception));
        super.logStackTrace(exception);

        return ResponseEntity
                .status(exception.getStatusCode())
                .headers(super.mapHeaders(request))
                .body(responseBody);
    }
}
