package co.com.hospital.domain.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetPartialPatientDTO{
    protected Long id;
    protected String dni;
    protected String fullName;
    protected Integer age;
}
