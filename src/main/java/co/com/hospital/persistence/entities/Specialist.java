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
}
