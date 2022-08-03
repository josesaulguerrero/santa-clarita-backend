package co.com.hospital.domain.dto.specialty;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class UpdateSpecialtyDTO extends CreateSpecialtyDTO {
    private Long id;
}
