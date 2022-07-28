package co.com.hospital.domain.service;

import co.com.hospital.domain.dto.specialist.CreateSpecialistDTO;
import co.com.hospital.domain.dto.specialist.DetailedSpecialistDTO;
import co.com.hospital.domain.dto.specialist.PartialSpecialistDTO;
import co.com.hospital.persistence.entities.Specialist;
import co.com.hospital.persistence.entities.Specialty;
import co.com.hospital.persistence.mapper.SpecialistMapper;
import co.com.hospital.persistence.repository.SpecialistRepository;
import co.com.hospital.utils.HttpException;
import co.com.hospital.utils.HttpExceptionBuilder;
import co.com.hospital.utils.RuntimeExceptionBuilder;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@Service
@Transactional
@AllArgsConstructor
public class SpecialistService {
    private final SpecialistRepository repository;
    private final SpecialistMapper mapper;

    public List<PartialSpecialistDTO> findAll() {
        List<Specialist> entities = this.repository.findAll();
        return this.mapper.entitiesToPartialDTOs(entities);
    }

    public List<PartialSpecialistDTO> findAvaliable() {
        List<Specialist> entities = this.repository.findAllByIsAvailableTrue();
        return this.mapper.entitiesToPartialDTOs(entities);
    }

    private Supplier<? extends RuntimeException> getNotFoundExceptionSupplier() {
        return () -> new HttpExceptionBuilder()
                .developerMessage("The given id does not belong to any Specialist")
                .statusCode(HttpStatus.NOT_FOUND)
                .build();
    }

    public DetailedSpecialistDTO findById(Long id) {
        Specialist entity = this.repository
                .findById(id)
                .orElseThrow(this.getNotFoundExceptionSupplier());
        return this.mapper.entityToDetailedDTO(entity);
    }

    public DetailedSpecialistDTO create(CreateSpecialistDTO dto) {
        Specialist entityFromDTO = this.mapper.createDTOToEntity(dto);
        Specialist savedEntity = this.repository.save(entityFromDTO);
        return this.mapper.entityToDetailedDTO(savedEntity);
    }

    private DetailedSpecialistDTO update(Specialist specialist) {
        Specialist savedEntity = this.repository.save(specialist);
        return this.mapper.entityToDetailedDTO(savedEntity);
    }

    public DetailedSpecialistDTO assignToSpeciality(Long specialistId, Specialty specialty) {
        Specialist specialist = this.repository
                .findById(specialistId)
                .orElseThrow(this.getNotFoundExceptionSupplier());
        specialist.setAssociatedSpecialty(specialty);
        specialist.setIsAvailable(false);
        return this.update(specialist);
    }

    public void unassignSpecialty(Long specialistId) {
        Specialist specialist = this.repository
                .findById(specialistId)
                .orElseThrow(this.getNotFoundExceptionSupplier());
        specialist.setIsAvailable(true);
        specialist.setAssociatedSpecialty(null);
        this.update(specialist);
    }

    public DetailedSpecialistDTO delete(Long id) {
        DetailedSpecialistDTO dto = this.findById(id);
        this.repository.deleteById(dto.getId());
        return dto;
    }
}
