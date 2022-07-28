package co.com.hospital.domain.dto.specialist;

import co.com.hospital.utils.RuntimeExceptionBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAndUpdateSpecialistDTO {
    protected Long id;
    protected String dni;
    protected String fullName;
    protected Integer age;
    private Boolean isAvailable;
    private Long specialtyId;

    private boolean idIsNull() {
        return this.id != null;
    }

    private boolean availableIsNull() {
        return this.isAvailable == null;
    }

    private boolean specialtyIdIsNull() {
        return this.specialtyId == null;
    }

    public boolean isValidCreationDTO() {
        Optional<String> message = Optional.empty();
        if (!this.idIsNull()) {
            message = Optional.of("The given DTO shouldn't have an Id field; It will be automatically assigned by the database.");
        }
        if (!this.availableIsNull()) {
            message = Optional.of("The availability of an specialist is inferred; Do not specify it directly.");
        }
        if (!this.specialtyIdIsNull()) {
            message = Optional.of("An specialist starts being unassigned to any speciality; Do not try to assign it directly.");
        }
        if(message.isPresent()) {
            throw new RuntimeExceptionBuilder(IllegalArgumentException.class)
                    .developerMessage(message.get())
                    .build();
        }
        return true;
    }

    public boolean isValidUpdateDTO() {
        Optional<String> message = Optional.empty();
        if (this.idIsNull()) {
            message = Optional.of("The given DTO does not have an Id field; You should supply one.");
        }
        if (this.availableIsNull()) {
            message = Optional.of("The availability of an specialist cannot be updated directly.");
        }
        if (this.specialtyIdIsNull()) {
            message = Optional.of("An specialist cannot be assigned to a specialty directly.");
        }
        if(message.isPresent()) {
            throw new RuntimeExceptionBuilder(IllegalArgumentException.class)
                    .developerMessage(message.get())
                    .build();
        }
        return true;
    }
}
