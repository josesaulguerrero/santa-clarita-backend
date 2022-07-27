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
@ToString
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "fk_clinical_history", nullable = false, updatable = false, insertable = false)
    private ClinicalHistory associatedClinicalHistory;

    // TODO set up relationship with Specialty.
}
