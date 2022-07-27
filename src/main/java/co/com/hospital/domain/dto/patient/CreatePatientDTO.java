package co.com.hospital.domain.dto.patient;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreatePatientDTO {
    private String dni;
    private String fullName;
    private Integer age;
}
