package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.patient.CreatePatientDTO;
import co.com.hospital.domain.dto.patient.DetailedPatientDTO;
import co.com.hospital.domain.dto.patient.PartialPatientDTO;
import co.com.hospital.domain.service.PatientService;
import co.com.hospital.utils.HttpException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patients")
@AllArgsConstructor
public class PatientController {
    private final PatientService service;

    @GetMapping
    public ResponseEntity<List<PartialPatientDTO>> getAll() {
        try {
            return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailedPatientDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
        /*try {
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }*/
    }

    @PostMapping
    public ResponseEntity<DetailedPatientDTO> post(@RequestBody CreatePatientDTO dto) {
        try {
            return new ResponseEntity<>(this.service.create(dto), HttpStatus.CREATED);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}
