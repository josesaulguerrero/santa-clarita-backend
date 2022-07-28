package co.com.hospital.domain.service;

import co.com.hospital.domain.dto.specialist.DetailedSpecialistDTO;
import co.com.hospital.domain.dto.specialty.CreateAndUpdateSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.DetailedSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.PartialSpecialtyDTO;
import co.com.hospital.persistence.entities.Specialist;
import co.com.hospital.persistence.entities.Specialty;
import co.com.hospital.persistence.mapper.SpecialistMapper;
import co.com.hospital.persistence.mapper.SpecialtyMapper;
import co.com.hospital.persistence.repository.SpecialtyRepository;
import co.com.hospital.utils.RuntimeExceptionBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SpecialtyService {
    private final SpecialtyRepository repository;
    private final SpecialtyMapper mapper;
    private final SpecialistService specialistService;
    private final SpecialistMapper specialistMapper;


    public List<PartialSpecialtyDTO> findAll() {
        List<Specialty> entities = this.repository.findAll();
        return this.mapper.entitiesToPartialDTOs(entities);
    }

    public DetailedSpecialtyDTO findById(Long id) {
        Specialty entity = this.repository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeExceptionBuilder(IllegalArgumentException.class)
                                .developerMessage("The given id does not belong to any Specialty in this hospital.")
                                .build()
                );
        return this.mapper.entityToDetailedDTO(entity);
    }

    public DetailedSpecialtyDTO create(CreateAndUpdateSpecialtyDTO dto) {
        Specialty entityFromDTO = this.mapper.createDTOToEntity(dto);
        DetailedSpecialistDTO associatedSpecialistDTO = this.specialistService.findById(
                dto.getSpecialistId()
        );
        Specialist specialist = this.specialistMapper.detailedDTOToEntity(associatedSpecialistDTO);
        // sets up the relationship between specialist and specialty.
        specialist.setAssociatedSpecialty(entityFromDTO);
        entityFromDTO.setSpecialistInCharge(specialist);
        Specialty savedEntity = this.repository.save(entityFromDTO);
        return this.mapper.entityToDetailedDTO(savedEntity);
    }

    public DetailedSpecialtyDTO update(CreateAndUpdateSpecialtyDTO dto) {
        
    }

    public DetailedSpecialtyDTO assignSpecialist() {

    }

    public DetailedSpecialtyDTO delete(Long id) {

    }
}
