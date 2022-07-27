package co.com.hospital.domain.dto.specialist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@NoArgsConstructor
@Getter
public class DetailedSpecialistDTO extends PartialSpecialistDTO {
    private boolean isAvailable;
    private Long specialtyId;

    public DetailedSpecialistDTO(Long id, String dni, String fullName, Integer age, boolean isAvailable, Long specialtyId) {
        super(id, dni, fullName, age);
        this.isAvailable = isAvailable;
        this.specialtyId = specialtyId;
    }
}
