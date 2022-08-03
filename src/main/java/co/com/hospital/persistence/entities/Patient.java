package co.com.hospital.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "patients")
@NoArgsConstructor
@Getter
@Setter
public class Patient extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "associatedPatient", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
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

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                '}';

    }
}
