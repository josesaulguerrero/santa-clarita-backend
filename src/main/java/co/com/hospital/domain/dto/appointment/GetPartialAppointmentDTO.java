package co.com.hospital.domain.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetPartialAppointmentDTO {
    protected Long id;
    protected LocalDateTime date;
}
