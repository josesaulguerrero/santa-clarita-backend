package co.com.hospital.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clinical_histories")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@ToString
public class ClinicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "associatedClinicalHistory", orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Appointment> appointments;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_patient", unique = true)
    @ToString.Exclude
    private Patient associatedPatient;

    public ClinicalHistory(Long id) {
        this.id = id;
    }

    public ClinicalHistory(List<Appointment> appointments, Patient associatedPatient) {
        this.appointments = appointments;
        this.associatedPatient = associatedPatient;
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }
}
