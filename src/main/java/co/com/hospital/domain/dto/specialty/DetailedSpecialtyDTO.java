package co.com.hospital.domain.dto.specialty;

import co.com.hospital.domain.dto.appointment.PartialAppointmentDTO;
import co.com.hospital.domain.dto.specialist.PartialSpecialistDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@ToString(callSuper = true)
public class DetailedSpecialtyDTO extends PartialSpecialtyDTO {
    private PartialSpecialistDTO specialistInCharge;

    public DetailedSpecialtyDTO(Long id, String name, PartialSpecialistDTO specialistInCharge) {
        super(id, name);
        this.specialistInCharge = specialistInCharge;
    }
}
