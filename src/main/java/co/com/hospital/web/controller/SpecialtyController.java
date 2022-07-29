package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.specialty.CreateAndUpdateSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.DetailedSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.PartialSpecialtyDTO;
import co.com.hospital.domain.service.SpecialtyService;
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

    @GetMapping
    public ResponseEntity<List<PartialSpecialtyDTO>> getAll() {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailedSpecialtyDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DetailedSpecialtyDTO> post(@RequestBody CreateAndUpdateSpecialtyDTO dto) {
        return new ResponseEntity<>(this.service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DetailedSpecialtyDTO> update(@RequestBody CreateAndUpdateSpecialtyDTO dto) {
        return new ResponseEntity<>(this.service.update(dto), HttpStatus.ACCEPTED);
    }

    @PutMapping("specialist")
    public ResponseEntity<DetailedSpecialtyDTO> assignNewSpecialist(@RequestBody CreateAndUpdateSpecialtyDTO dto) {
        return new ResponseEntity<>(this.service.assignSpecialist(dto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DetailedSpecialtyDTO> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
    }
}
