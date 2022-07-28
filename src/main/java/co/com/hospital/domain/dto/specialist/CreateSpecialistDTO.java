package co.com.hospital.domain.dto.specialist;

import co.com.hospital.utils.RuntimeExceptionBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSpecialistDTO {
    protected Long id;
    protected String dni;
    protected String fullName;
    protected Integer age;

    private boolean idIsNull() {
        return this.id != null;
    }

    public boolean isValidDTO() {
        Optional<String> message = Optional.empty();
        if (!this.idIsNull()) {
            message = Optional.of("The given DTO shouldn't have an Id field; It will be automatically assigned by the database.");
        }
        if(message.isPresent()) {
            throw new RuntimeExceptionBuilder(IllegalArgumentException.class)
                    .developerMessage(message.get())
                    .build();
        }
        return true;
    }
}
