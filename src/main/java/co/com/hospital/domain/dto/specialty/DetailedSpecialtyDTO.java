package co.com.hospital.domain.dto.specialty;

import co.com.hospital.domain.dto.appointment.PartialAppointmentDTO;
import co.com.hospital.domain.dto.specialist.PartialSpecialistDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class DetailedSpecialtyDTO extends PartialSpecialtyDTO {
    private PartialSpecialistDTO specialistInCharge;
    private List<PartialAppointmentDTO> associatedAppointments;

    public DetailedSpecialtyDTO(Long id, String name, PartialSpecialistDTO specialistInCharge, List<PartialAppointmentDTO> associatedAppointments) {
        super(id, name);
        this.specialistInCharge = specialistInCharge;
        this.associatedAppointments = associatedAppointments;
    }
}
