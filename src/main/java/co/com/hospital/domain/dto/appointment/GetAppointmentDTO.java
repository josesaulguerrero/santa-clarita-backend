package co.com.hospital.domain.dto.appointment;

import co.com.hospital.persistence.entities.ClinicalHistory;
import co.com.hospital.persistence.entities.Specialty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GetAppointmentDTO extends GetPartialAppointmentDTO {
    private ClinicalHistory clinicalHistory;
    private Specialty specialty;
}
