package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.patient.CreatePatientDTO;
import co.com.hospital.domain.dto.patient.DetailedPatientDTO;
import co.com.hospital.domain.dto.patient.PartialPatientDTO;
import co.com.hospital.domain.dto.specialist.CreateSpecialistDTO;
import co.com.hospital.domain.dto.specialist.DetailedSpecialistDTO;
import co.com.hospital.domain.dto.specialist.PartialSpecialistDTO;
import co.com.hospital.domain.service.SpecialistService;
import co.com.hospital.utils.HttpException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("specialists")
@AllArgsConstructor
public class SpecialistController {
    private final SpecialistService service;

    @GetMapping
    public ResponseEntity<List<PartialSpecialistDTO>> getAll() {
        try {
            return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("available")
    public ResponseEntity<List<PartialSpecialistDTO>> getAllAvailable() {
        try {
            return new ResponseEntity<>(this.service.findAvaliable(), HttpStatus.OK);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailedSpecialistDTO> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @PostMapping
    public ResponseEntity<DetailedSpecialistDTO> post(@RequestBody CreateSpecialistDTO dto) {
        try {
            return new ResponseEntity<>(this.service.create(dto), HttpStatus.CREATED);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DetailedSpecialistDTO> delete(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}
