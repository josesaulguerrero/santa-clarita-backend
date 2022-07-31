package co.com.hospital.web.mapper;

import co.com.hospital.domain.dto.specialist.PartialSpecialistDTO;
import co.com.hospital.domain.dto.specialty.CreateAndUpdateSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.DetailedSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.PartialSpecialtyDTO;
import co.com.hospital.persistence.entities.Specialist;
import co.com.hospital.persistence.entities.Specialty;
import lombok.AllArgsConstructor;

import java.util.List;

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
                .toList();
    }

    public DetailedSpecialtyDTO entityToDetailedDTO(Specialty entity) {
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
                .map(this::entityToDetailedDTO)
                .toList();
    }

    public Specialty createDTOToEntity(CreateAndUpdateSpecialtyDTO dto) {
        return new Specialty(
                dto.getName(),
                new Specialist(dto.getSpecialistId())
        );
    }

    public Specialty updateDTOToEntity(CreateAndUpdateSpecialtyDTO dto) {
        return new Specialty(
                dto.getId(),
                dto.getName(),
                new Specialist(dto.getSpecialistId())
        );
    }
}
