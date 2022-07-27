package co.com.hospital.domain.dto.patient;

import lombok.*;

@ToString(callSuper = true)
@NoArgsConstructor
@Getter
public class DetailedGetPatientDTO extends PartialPatientDTO {
    // TODO add a list with the assigned appointments.
    // TODO add all-args constructor that calls super().
    // TODO FieldDTO to validate updatable fields?
}

