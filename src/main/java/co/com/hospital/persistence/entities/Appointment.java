package co.com.hospital.persistence.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_clinical_history")
    @ToString.Exclude
    private ClinicalHistory associatedClinicalHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_specialty")
    @ToString.Exclude
    private Specialty specialtyInCharge;

    public Appointment(Long id) {
        this.id = id;
    }

    public Appointment(LocalDateTime date, ClinicalHistory associatedClinicalHistory, Specialty specialtyInCharge) {
        this.date = date;
        this.associatedClinicalHistory = associatedClinicalHistory;
        this.specialtyInCharge = specialtyInCharge;
    }
}
