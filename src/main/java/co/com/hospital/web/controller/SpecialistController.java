package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.patient.CreatePatientDTO;
import co.com.hospital.domain.dto.patient.DetailedPatientDTO;
import co.com.hospital.domain.dto.patient.PartialPatientDTO;
import co.com.hospital.domain.dto.specialist.CreateSpecialistDTO;
import co.com.hospital.domain.dto.specialist.DetailedSpecialistDTO;
import co.com.hospital.domain.dto.specialist.PartialSpecialistDTO;
import co.com.hospital.domain.service.SpecialistService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("specialists")
@AllArgsConstructor
public class SpecialistController {
    private final SpecialistService service;

    @GetMapping
    public List<PartialSpecialistDTO> getAll() {
        return this.service.findAll();
    }

    @GetMapping("available")
    public List<PartialSpecialistDTO> getAllAvailable() {
    }

    @GetMapping("{id}")
    public DetailedSpecialistDTO getById(@PathVariable("id") Long id) {
        return this.service.findById(id);
    }

    @PostMapping
    public DetailedSpecialistDTO post(@RequestBody CreateSpecialistDTO dto) {
        return this.service.create(dto);
    }
}
