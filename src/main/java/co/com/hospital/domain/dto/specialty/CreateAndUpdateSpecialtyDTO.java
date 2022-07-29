package co.com.hospital.domain.dto.specialty;

import co.com.hospital.utils.RuntimeExceptionBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAndUpdateSpecialtyDTO {
    protected Long id;
    protected String name;
    private Long specialistId;

    private boolean idIsNull() {
        return this.getId() == null;
    }

    public boolean isValidCreationDTO() {
        if (!this.idIsNull()) {
            throw new RuntimeExceptionBuilder(IllegalArgumentException.class)
                    .developerMessage("The given DTO shouldn't have an Id field; It will be automatically assigned by the database.")
                    .build();
        }
        return true;
    }

    public boolean isValidUpdateDTO() {
        if (this.idIsNull()) {
            throw new RuntimeExceptionBuilder(IllegalArgumentException.class)
                    .developerMessage("The given DTO does not have an Id field; You should supply one.")
                    .build();
        }
        return true;
    }
}
