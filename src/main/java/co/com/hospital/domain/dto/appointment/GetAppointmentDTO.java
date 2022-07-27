package co.com.hospital.domain.dto.appointment;

import co.com.hospital.persistence.entities.ClinicalHistory;
import co.com.hospital.persistence.entities.Specialty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@NoArgsConstructor
@Getter
public class GetAppointmentDTO extends GetPartialAppointmentDTO {
    // TODO add the specialty partial DTO.
    // TODO add all-args constructor that calls super().
}
