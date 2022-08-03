package co.com.hospital.domain.dto.patient;

import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePatientDTO {
    @NotNull(message = "A patient's DNI cannot be null.")
    @NotBlank(message = "A patient's DNI cannot be an empty string.")
    private String dni;

    @NotNull(message = "A patient's name cannot be null.")
    @NotBlank(message = "A patient's name cannot be an empty string.")
    private String fullName;

    @NotNull(message = "You cannot supply a nullable value for the age of a patient.")
    @Positive(message = "You cannot supply a negative or zero value for the age of a patient.")
    @Max(value = 115, message = "The age of a patient is over 115? Are you kidding me?")
    private Integer age;
}
