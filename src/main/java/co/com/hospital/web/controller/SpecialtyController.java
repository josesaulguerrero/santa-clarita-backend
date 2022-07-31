package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.specialist.PartialSpecialistDTO;
import co.com.hospital.domain.dto.specialty.CreateAndUpdateSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.DetailedSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.PartialSpecialtyDTO;
import co.com.hospital.domain.service.SpecialtyService;
import co.com.hospital.persistence.entities.Specialist;
import co.com.hospital.persistence.entities.Specialty;
import co.com.hospital.web.mapper.SpecialistMapper;
import co.com.hospital.web.mapper.SpecialtyMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("specialties")
@AllArgsConstructor
@CrossOrigin("*")
public class SpecialtyController {
    private final SpecialtyService service;
    private final SpecialtyMapper mapper;
    private final SpecialistMapper specialistMapper;

    @GetMapping
    public ResponseEntity<List<PartialSpecialtyDTO>> getAll() {
        List<PartialSpecialtyDTO> DTOs = this.mapper.entitiesToPartialDTOs(
                this.service.findAll()
        );
        return new ResponseEntity<>(DTOs, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailedSpecialtyDTO> getById(@PathVariable("id") Long id) {
        DetailedSpecialtyDTO DTO = this.mapper.entityToDetailedDTO(
                this.service.findById(id)
        );
        return new ResponseEntity<>(DTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DetailedSpecialtyDTO> post(@RequestBody CreateAndUpdateSpecialtyDTO dto) {
        dto.validateCreationDTO();
        Specialty entityFromDTO = this.mapper.createDTOToEntity(dto);
        DetailedSpecialtyDTO savedEntityDTO = this.mapper.entityToDetailedDTO(
                this.service.create(entityFromDTO)
        );
        return new ResponseEntity<>(savedEntityDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DetailedSpecialtyDTO> update(@RequestBody CreateAndUpdateSpecialtyDTO dto) {
        dto.validateUpdateDTO();
        Specialty entityFromDTO = this.mapper.updateDTOToEntity(dto);
        DetailedSpecialtyDTO updatedEntityDTO = this.mapper.entityToDetailedDTO(
                this.service.update(entityFromDTO)
        );
        return new ResponseEntity<>(updatedEntityDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping("{specialtyId}/specialist")
    public ResponseEntity<DetailedSpecialtyDTO> assignNewSpecialist(
            @PathVariable("specialtyId") Long specialtyId,
            @RequestBody PartialSpecialistDTO dto
    ) {
        DetailedSpecialtyDTO updatedEntityDTO = this.mapper.entityToDetailedDTO(
                this.service.assignSpecialist(specialtyId, dto.getId())
        );
        return new ResponseEntity<>(updatedEntityDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DetailedSpecialtyDTO> delete(@PathVariable("id") Long id) {
        DetailedSpecialtyDTO DTO = this.mapper.entityToDetailedDTO(
                this.service.delete(id)
        );
        return new ResponseEntity<>(DTO, HttpStatus.OK);
    }
}
