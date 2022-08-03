package co.com.hospital.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "specialties")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "associatedSpecialty", fetch = FetchType.LAZY)
    private Specialist specialistInCharge;

    public Specialty(Long id) {
        this.id = id;
    }

    public Specialty(String name, Specialist specialistInCharge) {
        this.name = name;
        this.specialistInCharge = specialistInCharge;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialistInCharge=" + specialistInCharge +
                '}';
    }
}
