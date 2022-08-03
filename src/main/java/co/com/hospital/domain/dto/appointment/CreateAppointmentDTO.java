package co.com.hospital.domain.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAppointmentDTO {
    @NotNull(message = "You cannot supply a nullable value for the patient Id.")
    @Positive(message = "You cannot supply a negative or zero value for the patient Id.")
    private Long patientId;

    @NotNull(message = "You cannot supply a nullable value for the specialty Id.")
    @Positive(message = "You cannot supply a negative or zero value for the specialty Id.")
    private Long specialtyId;
}
