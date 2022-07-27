package co.com.hospital.domain.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class GetPatientDTO extends GetPartialPatientDTO {
    // TODO add the clinical history DTO.
    // TODO add all-args constructor that calls super().
    // TODO FieldDTO to validate updatable fields?
}
