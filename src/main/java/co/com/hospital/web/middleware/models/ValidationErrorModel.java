package co.com.hospital.web.middleware.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ValidationErrorModel {
    private String message;
    private List<String> errors;
}
