package co.com.hospital.domain.dto.patient;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAndUpdatePatientDTO {
    private Long id;
    private String dni;
    private String fullName;
    private Integer age;

    private boolean idIsNull() {
        return this.getId() != null;
    }

    public boolean isValidCreationDTO() {
        if (!this.idIsNull()) {
            throw new IllegalArgumentException("The given DTO shouldn't have an Id field; It will be automatically assigned by the database.");
        }
        return true;
    }

    public boolean isValidUpdateDTO() {
        if (this.idIsNull()) {
            throw new IllegalArgumentException("The given DTO does not have an Id field; You should supply one.");
        }
        return true;
    }
}
