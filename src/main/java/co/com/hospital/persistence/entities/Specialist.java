package co.com.hospital.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "specialists")
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Specialist extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_specialty")
    private Specialty associatedSpecialty;

    public Specialist(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Specialist{" +
                "id=" + id +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
