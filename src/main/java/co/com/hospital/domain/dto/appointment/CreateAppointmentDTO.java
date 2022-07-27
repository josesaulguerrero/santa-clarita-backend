package co.com.hospital.domain.dto.appointment;

import co.com.hospital.utils.RuntimeExceptionBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAppointmentDTO {
    private Long id;
    private LocalDateTime date;
    private Long clinicalHistoryId;
    private Long specialtyId;

    private boolean idIsNull() {
        return this.getId() != null;
    }

    public boolean isValidCreationDTO() {
        if (!this.idIsNull()) {
            throw new RuntimeExceptionBuilder(IllegalArgumentException.class)
                    .developerMessage("The given DTO shouldn't have an Id field; It will be automatically assigned by the database.")
                    .build();
        }
        return true;
    }
}
