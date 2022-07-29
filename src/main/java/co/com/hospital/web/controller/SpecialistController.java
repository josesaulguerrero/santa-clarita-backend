package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.specialist.CreateSpecialistDTO;
import co.com.hospital.domain.dto.specialist.DetailedSpecialistDTO;
import co.com.hospital.domain.dto.specialist.PartialSpecialistDTO;
import co.com.hospital.domain.service.SpecialistService;
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

    @GetMapping
    public ResponseEntity<List<PartialSpecialistDTO>> getAll() {
        return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
    }

    @GetMapping("available")
    public ResponseEntity<List<PartialSpecialistDTO>> getAllAvailable() {
        return new ResponseEntity<>(this.service.findAvaliable(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailedSpecialistDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DetailedSpecialistDTO> post(@RequestBody CreateSpecialistDTO dto) {
        return new ResponseEntity<>(this.service.create(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DetailedSpecialistDTO> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
    }
}
