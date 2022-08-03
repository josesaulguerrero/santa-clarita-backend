package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.specialist.CreateSpecialistDTO;
import co.com.hospital.domain.dto.specialist.DetailedSpecialistDTO;
import co.com.hospital.domain.dto.specialist.PartialSpecialistDTO;
import co.com.hospital.domain.service.SpecialistService;
import co.com.hospital.persistence.entities.Specialist;
import co.com.hospital.web.mapper.SpecialistMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("specialists")
@CrossOrigin("*")
@AllArgsConstructor
public class SpecialistController {
    private final SpecialistService service;
    private final SpecialistMapper mapper;

    @GetMapping("all")
    public ResponseEntity<List<PartialSpecialistDTO>> getAll() {
        List<PartialSpecialistDTO> DTOs = this.mapper.entitiesToPartialDTOs(
                this.service.findAll()
        );
        return new ResponseEntity<>(DTOs, HttpStatus.OK);
    }

    @GetMapping("all/available")
    public ResponseEntity<List<PartialSpecialistDTO>> getAllAvailable() {
        List<PartialSpecialistDTO> DTOs = this.mapper.entitiesToPartialDTOs(
                this.service.findAvailable()
        );
        return new ResponseEntity<>(DTOs, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailedSpecialistDTO> getById(@PathVariable("id") Long id) {
        DetailedSpecialistDTO dto = this.mapper.entityToDetailedDTO(
                this.service.findById(id)
        );
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<DetailedSpecialistDTO> post(@RequestBody CreateSpecialistDTO dto) {
        Specialist savedEntity = this.service.create(dto);
        DetailedSpecialistDTO savedEntityDTO = this.mapper.entityToDetailedDTO(savedEntity);
        return new ResponseEntity<>(savedEntityDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DetailedSpecialistDTO> delete(@PathVariable("id") Long id) {
        DetailedSpecialistDTO dto = this.mapper.entityToDetailedDTO(
                this.service.delete(id)
        );
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
