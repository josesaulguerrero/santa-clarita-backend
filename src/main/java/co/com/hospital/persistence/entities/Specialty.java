package co.com.hospital.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "specialties")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "fk_specialist", insertable = false, updatable = false)
    private Specialist specialistInCharge;

    public Specialty(String name, Specialist specialistInCharge) {
        this.name = name;
        this.specialistInCharge = specialistInCharge;
    }
}
