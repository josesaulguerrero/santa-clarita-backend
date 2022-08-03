package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.patient.CreatePatientDTO;
import co.com.hospital.domain.dto.patient.DetailedPatientDTO;
import co.com.hospital.domain.dto.patient.PartialPatientDTO;
import co.com.hospital.domain.service.PatientService;
import co.com.hospital.persistence.entities.Patient;
import co.com.hospital.web.mapper.PatientMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("patients")
@AllArgsConstructor
@CrossOrigin("*")
public class PatientController {
    private final PatientService service;
    private final PatientMapper mapper;

    @GetMapping("all")
    public ResponseEntity<List<PartialPatientDTO>> getAll() {
        List<PartialPatientDTO> mappedPatients = this.mapper.entitiesToPartialDTOs(this.service.findAll());
        return new ResponseEntity<>(mappedPatients, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailedPatientDTO> getById(@PathVariable("id") Long id) {
        DetailedPatientDTO mappedPatient = this.mapper.entityToDetailedDTO(this.service.findById(id));
        return new ResponseEntity<>(mappedPatient, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<DetailedPatientDTO> post(@RequestBody @Valid CreatePatientDTO dto) {
        Patient savedEntity = this.service.create(dto);
        DetailedPatientDTO mappedPatient = this.mapper.entityToDetailedDTO(savedEntity);
        return new ResponseEntity<>(mappedPatient, HttpStatus.CREATED);
    }
}
