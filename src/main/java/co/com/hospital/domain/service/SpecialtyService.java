package co.com.hospital.domain.service;

import co.com.hospital.domain.dto.specialty.CreateAndUpdateSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.DetailedSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.PartialSpecialtyDTO;
import co.com.hospital.persistence.entities.Specialist;
import co.com.hospital.persistence.entities.Specialty;
import co.com.hospital.persistence.mapper.SpecialtyMapper;
import co.com.hospital.persistence.repository.SpecialtyRepository;
import co.com.hospital.utils.HttpExceptionBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@Service
@Transactional
@AllArgsConstructor
public class SpecialtyService {
    private final SpecialtyRepository repository;
    private final SpecialtyMapper mapper;
    private final SpecialistService specialistService;


    public List<PartialSpecialtyDTO> findAll() {
        List<Specialty> entities = this.repository.findAll();
        return this.mapper.entitiesToPartialDTOs(entities);
    }

    private Supplier<? extends RuntimeException> getNotFoundExceptionSupplier() {
        return () -> new HttpExceptionBuilder()
                .developerMessage("The given id does not belong to any Specialty in this hospital.")
                .statusCode(HttpStatus.NOT_FOUND)
                .build();
    }

    public DetailedSpecialtyDTO findById(Long id) {
        Specialty entity = this.repository
                .findById(id)
                .orElseThrow(this.getNotFoundExceptionSupplier());
        return this.mapper.entityToDetailedDTO(entity);
    }

    public Boolean exists(Long id) {
        return this.repository.existsById(id);
    }

    public DetailedSpecialtyDTO create(CreateAndUpdateSpecialtyDTO dto) {
        dto.isValidCreationDTO(); // validates dto attributes.
        Specialty entityFromDTO = this.mapper.createDTOToEntity(dto);
        Specialist specialist = this.specialistService.assignToSpeciality(
                dto.getSpecialistId(), entityFromDTO
        );
        entityFromDTO.setSpecialistInCharge(specialist);
        Specialty savedEntity = this.repository.save(entityFromDTO);
        return this.mapper.entityToDetailedDTO(savedEntity);
    }

    public DetailedSpecialtyDTO update(CreateAndUpdateSpecialtyDTO dto) {
        dto.isValidUpdateDTO();
        this.findById(dto.getId());
        Specialty entityFromDTO = this.mapper.updateDTOToEntity(dto);
        Specialty updatedEntity = this.repository.save(entityFromDTO);
        return this.mapper.entityToDetailedDTO(updatedEntity);
    }

    public DetailedSpecialtyDTO assignSpecialist(CreateAndUpdateSpecialtyDTO dto) {
        Specialty specialty = this.repository
                .findById(dto.getId())
                .orElseThrow(this.getNotFoundExceptionSupplier());
        this.specialistService.unassignSpecialty(dto.getSpecialistId());
        Specialist specialist = this.specialistService.assignToSpeciality(dto.getSpecialistId(), specialty);
        specialty.setSpecialistInCharge(specialist);
        Specialty updatedEntity = this.repository.save(specialty);
        return this.mapper.entityToDetailedDTO(updatedEntity);
    }

    public DetailedSpecialtyDTO delete(Long id) {
        Specialty specialty = this.repository
                .findById(id)
                .orElseThrow(this.getNotFoundExceptionSupplier());
        Long associatedAppointmentsCount = this.repository.countAssociatedAppointments(specialty.getId());
        if(associatedAppointmentsCount > 0) {
            throw new HttpExceptionBuilder()
                    .developerMessage("This specialty has associated appointments; You cannot delete it.")
                    .statusCode(HttpStatus.LOCKED)
                    .build();
        }
        this.repository.deleteById(specialty.getId());
        return this.mapper.entityToDetailedDTO(specialty);
    }
}
