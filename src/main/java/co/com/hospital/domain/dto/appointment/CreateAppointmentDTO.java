package co.com.hospital.domain.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAppointmentDTO {
    private Long patientId;
    private Long specialtyId;
}
