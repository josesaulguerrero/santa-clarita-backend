package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.specialty.CreateSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.DetailedSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.PartialSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.UpdateSpecialtyDTO;
import co.com.hospital.domain.service.SpecialtyService;
import co.com.hospital.persistence.entities.Specialty;
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

    @GetMapping("all")
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

    @PostMapping("save")
    public ResponseEntity<DetailedSpecialtyDTO> post(@RequestBody CreateSpecialtyDTO dto) {
        Specialty savedEntity = this.service.create(dto);
        DetailedSpecialtyDTO savedEntityDTO = this.mapper.entityToDetailedDTO(savedEntity);
        return new ResponseEntity<>(savedEntityDTO, HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<DetailedSpecialtyDTO> update(@RequestBody UpdateSpecialtyDTO dto) {
        Specialty updatedEntity = this.service.update(dto);
        DetailedSpecialtyDTO updatedEntityDTO = this.mapper.entityToDetailedDTO(updatedEntity);
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
