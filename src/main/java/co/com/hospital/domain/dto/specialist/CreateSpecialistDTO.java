package co.com.hospital.domain.dto.specialist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSpecialistDTO {
    @NotNull(message = "A specialist's DNI cannot be null.")
    @NotBlank(message = "A specialist's DNI cannot be an empty string.")
    private String dni;

    @NotNull(message = "The name of a specialist cannot be null.")
    @NotBlank(message = "The name of a specialist cannot be an empty string.")
    private String fullName;

    @NotNull(message = "The age of a specialist cannot be null.")
    @Positive(message = "You cannot supply a negative or zero value for the age of a patient.")
    @Max(value = 75, message = "People over 75 are not allowed to work in this hospital.")
    private Integer age;
}
