package co.com.hospital.domain.dto.appointment;

import co.com.hospital.domain.dto.specialty.PartialSpecialtyDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString(callSuper = true)
@NoArgsConstructor
@Getter
public class DetailedAppointmentDTO extends PartialAppointmentDTO {
    private PartialSpecialtyDTO specialty;

    public DetailedAppointmentDTO(Long id, LocalDateTime date, PartialSpecialtyDTO specialty) {
        super(id, date);
        this.specialty = specialty;
    }
}
