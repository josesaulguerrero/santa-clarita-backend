package co.com.hospital.domain.dto.specialty;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class UpdateSpecialtyDTO extends CreateSpecialtyDTO {
    @NotNull(message = "The id of the Specialty you want to update cannot be null")
    @Positive(message = "You cannot supply a negative or zero value for the id of the Specialty.")
    private Long id;
}
