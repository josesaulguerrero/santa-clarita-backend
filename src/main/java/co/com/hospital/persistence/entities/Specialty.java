package co.com.hospital.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "specialties")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@ToString
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "associatedSpecialty", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Specialist specialistInCharge;

    public Specialty(Long id) {
        this.id = id;
    }

    public Specialty(String name, Specialist specialistInCharge) {
        this.name = name;
        this.specialistInCharge = specialistInCharge;
    }
}
