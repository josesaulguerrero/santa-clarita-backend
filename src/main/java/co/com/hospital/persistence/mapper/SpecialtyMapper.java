package co.com.hospital.persistence.mapper;

import co.com.hospital.domain.dto.specialist.PartialSpecialistDTO;
import co.com.hospital.domain.dto.specialty.CreateAndUpdateSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.DetailedSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.PartialSpecialtyDTO;
import co.com.hospital.persistence.entities.Specialty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyMapper {
    private SpecialistMapper specialistMapper;

    public PartialSpecialtyDTO entityToPartialDTO(Specialty entity) {
        return new PartialSpecialtyDTO(
                entity.getId(),
                entity.getName()
        );
    }

    public List<PartialSpecialtyDTO> entitiesToPartialDTOs(List<Specialty> entities) {
        return entities
                .stream()
                .map(this::entityToPartialDTO)
                .collect(Collectors.toList());
    }

    public DetailedSpecialtyDTO entitiyToDetailedDTO(Specialty entity) {
        PartialSpecialistDTO specialist = specialistMapper.entityToPartialDTO(
                entity.getSpecialistInCharge()
        );
        return new DetailedSpecialtyDTO(
                entity.getId(),
                entity.getName(),
                specialist
        );
    }

    public List<DetailedSpecialtyDTO> entitiesToDetailedDTOs(List<Specialty> entities) {
        return entities
                .stream()
                .map(this::entitiyToDetailedDTO)
                .collect(Collectors.toList());
    }

    public Specialty createDTOToEntity(CreateAndUpdateSpecialtyDTO dto) {
        return new Specialty(
                dto.getName(),
                null //TODO assing specialist in charge on the service.
        );
    }

    public Specialty updateDTOToEntity(CreateAndUpdateSpecialtyDTO dto) {
        return new Specialty(
                dto.getId(),
                dto.getName(),
                null //TODO assing specialist in charge on the service.
        );
    }
}
