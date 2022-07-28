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

    @OneToMany(mappedBy = "associatedClinicalHistory", fetch = FetchType.EAGER)
    private List<Appointment> appointments;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_patient", unique = true, insertable = false, updatable = false)
    private Patient associatedPatient;

    public ClinicalHistory(List<Appointment> appointments, Patient associatedPatient) {
        this.appointments = appointments;
        this.associatedPatient = associatedPatient;
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }
}
