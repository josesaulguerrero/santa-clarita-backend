package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.patient.CreatePatientDTO;
import co.com.hospital.domain.dto.patient.DetailedPatientDTO;
import co.com.hospital.domain.dto.patient.PartialPatientDTO;
import co.com.hospital.domain.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patients")
@AllArgsConstructor
@CrossOrigin("*")
public class PatientController {
    private final PatientService service;

    @GetMapping
    public ResponseEntity<List<PartialPatientDTO>> getAll() {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailedPatientDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DetailedPatientDTO> post(@RequestBody CreatePatientDTO dto) {
        return new ResponseEntity<>(this.service.create(dto), HttpStatus.CREATED);
    }
}
