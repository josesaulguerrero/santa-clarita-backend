package co.com.hospital.persistence.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clinical_histories")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ClinicalHistory {
    @Id
    @GenericGenerator(name = "clinical_history_id_generator", strategy = "uuid2")
    @GeneratedValue(generator = "clinical_history_id_generator")
    private String id;

    // TODO set up relationship to patient and associated appointments.
    // TODO add no-id constructor.
}
