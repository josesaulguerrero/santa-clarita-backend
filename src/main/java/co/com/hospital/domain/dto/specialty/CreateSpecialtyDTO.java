package co.com.hospital.domain.dto.specialty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSpecialtyDTO {
    @NotNull(message = "The name of a specialty cannot be null.")
    @NotBlank(message = "The name of a specialty cannot be an empty string.")
    protected String name;

    @NotNull(message = "The id of the associated specialist cannot be null.")
    @Positive(message = "You cannot supply a negative or zero value for the id of the associated specialist.")
    private Long specialistId;
}
