package co.com.hospital.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "specialists")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Specialist extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @OneToOne(mappedBy = "specialistInCharge")
    private Specialty associatedSpecialty;

    public Specialist(String DNI, String fullName, Integer age, Boolean isAvailable, Specialty associatedSpecialty) {
        super(DNI, fullName, age);
        this.isAvailable = isAvailable;
        this.associatedSpecialty = associatedSpecialty;
    }

    public Specialist(String DNI, String fullName, Integer age, Long id, Boolean isAvailable, Specialty associatedSpecialty) {
        super(DNI, fullName, age);
        this.id = id;
        this.isAvailable = isAvailable;
        this.associatedSpecialty = associatedSpecialty;
    }
}
