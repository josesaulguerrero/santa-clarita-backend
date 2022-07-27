package co.com.hospital.domain.dto.patient;

import co.com.hospital.domain.dto.ModifiableDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdatePatientDTO extends CreatePatientDTO {
    private Long id;

    public UpdatePatientDTO(String dni, String fullName, Integer age, Long id) {
        super(dni, fullName, age);
        this.id = id;
    }
}
