package co.com.hospital.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clinical_histories")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClinicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "associatedClinicalHistory", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_patient", unique = true)
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

    @Override
    public String toString() {
        return "ClinicalHistory{" +
                "id=" + id +
                ", appointments=" + appointments +
                ", associatedPatient=" + associatedPatient +
                '}';
    }
}
