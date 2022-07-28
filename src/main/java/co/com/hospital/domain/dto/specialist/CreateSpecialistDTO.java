package co.com.hospital.domain.dto.specialist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSpecialistDTO {
    protected String dni;
    protected String fullName;
    protected Integer age;
}
