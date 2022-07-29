package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.specialty.CreateAndUpdateSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.DetailedSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.PartialSpecialtyDTO;
import co.com.hospital.domain.service.SpecialtyService;
import co.com.hospital.utils.HttpException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("specialties")
@AllArgsConstructor
public class SpecialtyController {
    private final SpecialtyService service;

    @GetMapping
    public ResponseEntity<List<PartialSpecialtyDTO>> getAll() {
        try {
            return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailedSpecialtyDTO> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @PostMapping
    public ResponseEntity<DetailedSpecialtyDTO> post(@RequestBody CreateAndUpdateSpecialtyDTO dto) {
        try {
            return new ResponseEntity<>(this.service.create(dto), HttpStatus.CREATED);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @PutMapping
    public ResponseEntity<DetailedSpecialtyDTO> update(@RequestBody CreateAndUpdateSpecialtyDTO dto) {
        try {
            return new ResponseEntity<>(this.service.update(dto), HttpStatus.ACCEPTED);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @PutMapping("specialist")
    public ResponseEntity<DetailedSpecialtyDTO> assignNewSpecialist(@RequestBody CreateAndUpdateSpecialtyDTO dto) {
        try {
            return new ResponseEntity<>(this.service.assignSpecialist(dto), HttpStatus.ACCEPTED);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DetailedSpecialtyDTO> delete(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}
