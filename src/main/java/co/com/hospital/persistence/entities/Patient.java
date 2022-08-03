package co.com.hospital.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "patients")
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@ToString
public class Patient extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "associatedPatient", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private ClinicalHistory clinicalHistory;

    public Patient(Long id) {
        this.id = id;
    }

    public Patient(String DNI, String fullName, Integer age, ClinicalHistory clinicalHistory) {
        super(DNI, fullName, age);
        this.clinicalHistory = clinicalHistory;
    }

    public Patient(String DNI, String fullName, Integer age, Long id, ClinicalHistory clinicalHistory) {
        super(DNI, fullName, age);
        this.id = id;
        this.clinicalHistory = clinicalHistory;
    }
}
