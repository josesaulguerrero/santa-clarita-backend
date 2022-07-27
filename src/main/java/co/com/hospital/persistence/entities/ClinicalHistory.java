package co.com.hospital.persistence.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "associatedClinicalHistory")
    private List<Appointment> appointments;

    // TODO set up relationship to patient
    // TODO add no-id constructor.
}
