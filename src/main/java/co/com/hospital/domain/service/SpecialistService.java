package co.com.hospital.domain.service;

import co.com.hospital.domain.dto.specialist.CreateSpecialistDTO;
import co.com.hospital.domain.dto.specialist.DetailedSpecialistDTO;
import co.com.hospital.domain.dto.specialist.PartialSpecialistDTO;
import co.com.hospital.persistence.entities.Specialist;
import co.com.hospital.persistence.mapper.SpecialistMapper;
import co.com.hospital.persistence.repository.SpecialistRepository;
import co.com.hospital.utils.RuntimeExceptionBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public DetailedSpecialistDTO findById(Long id) {
        Specialist entity = this.repository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeExceptionBuilder(IllegalArgumentException.class)
                                .developerMessage("The given id does not belong to any Specialist")
                                .build()
                );
        return this.mapper.entityToDetailedDTO(entity);
    }

    public DetailedSpecialistDTO create(CreateSpecialistDTO dto) {
        Specialist entityFromDTO = this.mapper.createDTOToEntity(dto);
        Specialist savedEntity = this.repository.save(entityFromDTO);
        return this.mapper.entityToDetailedDTO(savedEntity);
    }

    public DetailedSpecialistDTO delete(Long id) {
        DetailedSpecialistDTO dto = this.findById(id);
        this.repository.deleteById(dto.getId());
        return dto;
    }
}
