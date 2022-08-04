package co.com.hospital.web.middleware;

import co.com.hospital.web.middleware.models.ValidationErrorModel;
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
import java.util.List;

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
        super.logStackTrace(exception);

        return ResponseEntity
                .badRequest()
                .body(this.buildModel(exception));
    }

    private ValidationErrorModel buildModel(MethodArgumentNotValidException exception) {
        String message = String.format("Validation failed %d time(s)", exception.getErrorCount());
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return ValidationErrorModel
                .builder()
                .message(message)
                .errors(errors)
                .build();
    }
}
