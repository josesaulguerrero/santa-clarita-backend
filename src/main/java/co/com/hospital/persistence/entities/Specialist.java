package co.com.hospital.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "specialists")
@AllArgsConstructor
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

    // TODO add relationship to associated department.

    public Specialist(String DNI, String fullName, Integer age, Boolean isAvailable) {
        super(DNI, fullName, age);
        this.isAvailable = isAvailable;
    }
}
