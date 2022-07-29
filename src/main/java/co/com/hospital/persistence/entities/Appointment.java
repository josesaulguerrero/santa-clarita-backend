package co.com.hospital.persistence.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "fk_clinical_history")
    private ClinicalHistory associatedClinicalHistory;

    @ManyToOne
    @JoinColumn(name = "fk_specialty", insertable = false)
    private Specialty specialtyInCharge;

    public Appointment(Long id) {
        this.id = id;
    }

    public Appointment(LocalDateTime date, ClinicalHistory associatedClinicalHistory, Specialty specialtyInCharge) {
        this.date = date;
        this.associatedClinicalHistory = associatedClinicalHistory;
        this.specialtyInCharge = specialtyInCharge;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + date +
                ", associatedClinicalHistory=" + associatedClinicalHistory +
                ", specialtyInCharge=" + specialtyInCharge +
                '}';
    }
}
