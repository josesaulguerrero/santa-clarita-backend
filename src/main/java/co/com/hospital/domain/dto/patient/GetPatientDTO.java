package co.com.hospital.domain.dto.patient;

import lombok.*;

@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GetPatientDTO extends GetPartialPatientDTO {
    // TODO add the clinical history DTO.
    // TODO add all-args constructor that calls super().
    // TODO FieldDTO to validate updatable fields?
}

