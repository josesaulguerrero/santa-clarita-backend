package co.com.hospital.domain.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetPartialPatientDTO{
    private Long id;
    private String dni;
    private String fullName;
    private Integer age;
}
