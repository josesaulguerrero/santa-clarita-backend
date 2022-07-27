package co.com.hospital.domain.dto.patient;

import lombok.*;

@ToString(callSuper = true)
@NoArgsConstructor
@Getter
public class GetPatientDTO extends GetPartialPatientDTO {
    // TODO add a list with the assigned appointments.
    // TODO add all-args constructor that calls super().
    // TODO FieldDTO to validate updatable fields?
}

