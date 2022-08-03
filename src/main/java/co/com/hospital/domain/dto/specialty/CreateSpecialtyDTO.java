package co.com.hospital.domain.dto.specialty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSpecialtyDTO {
    protected String name;
    private Long specialistId;
}
