package co.com.hospital.persistence.mapper;

import co.com.hospital.domain.dto.specialist.CreateSpecialistDTO;
import co.com.hospital.domain.dto.specialist.DetailedSpecialistDTO;
import co.com.hospital.domain.dto.specialist.PartialSpecialistDTO;
import co.com.hospital.persistence.entities.Specialist;
import co.com.hospital.persistence.entities.Specialty;

import java.util.List;
import java.util.stream.Collectors;

public class SpecialistMapper {
    public PartialSpecialistDTO entityToPartialDTO(Specialist entity) {
        return new PartialSpecialistDTO(
                entity.getId(),
                entity.getDNI(),
                entity.getFullName(),
                entity.getAge()
        );
    }

    public List<PartialSpecialistDTO> entitiesToPartialDTOs(List<Specialist> entities) {
        return entities
                .stream()
                .map(this::entityToPartialDTO)
                .toList();
    }

    public DetailedSpecialistDTO entityToDetailedDTO(Specialist entity) {
        Specialty specialty = entity.getAssociatedSpecialty();
        Long specialtyId =  specialty != null ? specialty.getId() : null;
        return new DetailedSpecialistDTO(
                entity.getId(),
                entity.getDNI(),
                entity.getFullName(),
                entity.getAge(),
                entity.getIsAvailable(),
                specialtyId //TODO Validate specialtyId isn't null.
        );
    }

    public List<DetailedSpecialistDTO> entitiesToDetailedDTOs(List<Specialist> entities) {
        return entities
                .stream()
                .map(this::entityToDetailedDTO)
                .toList();
    }

    public Specialist createDTOToEntity(CreateSpecialistDTO dto) {
        return new Specialist(
                dto.getDni(),
                dto.getFullName(),
                dto.getAge(),
                true, // specialists are avaialable by default when they are created.
                null // assign specialty if necessary on the service.
        );
    }
}
