package co.com.hospital.domain.dto.patient;

import co.com.hospital.domain.dto.appointment.PartialAppointmentDTO;
import lombok.*;

import java.util.List;

@ToString(callSuper = true)
@NoArgsConstructor
@Getter
public class DetailedPatientDTO extends PartialPatientDTO {
    private List<PartialAppointmentDTO> takenAppointments;

    public DetailedPatientDTO(Long id, String dni, String fullName, Integer age, List<PartialAppointmentDTO> takenAppointments) {
        super(id, dni, fullName, age);
        this.takenAppointments = takenAppointments;
    }

    //TODO FieldDTO to validate updatable fields?
}

