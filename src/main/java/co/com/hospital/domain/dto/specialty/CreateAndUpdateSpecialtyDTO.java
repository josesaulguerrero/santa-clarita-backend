package co.com.hospital.domain.dto.specialty;

import co.com.hospital.utils.HttpExceptionBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

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

    public void validateCreationDTO() {
        if (!this.idIsNull()) {
            throw new HttpExceptionBuilder()
                    .developerMessage("The given DTO shouldn't have an Id field; It will be automatically assigned by the database.")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    public void validateUpdateDTO() {
        if (this.idIsNull()) {
            throw new HttpExceptionBuilder()
                    .developerMessage("The given DTO does not have an Id field; You should supply one.")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }
}
