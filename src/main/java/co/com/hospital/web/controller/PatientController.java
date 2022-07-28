package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.patient.CreatePatientDTO;
import co.com.hospital.domain.dto.patient.DetailedPatientDTO;
import co.com.hospital.domain.dto.patient.PartialPatientDTO;
import co.com.hospital.domain.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("patients")
@AllArgsConstructor
public class PatientController {
    private final PatientService service;

    @GetMapping
    public List<PartialPatientDTO> getAll() {
        return this.service.findAll();
    }

    @GetMapping("{id}")
    public DetailedPatientDTO getById(@PathVariable("id") Long id) {
        return this.service.findById(id);
    }

    @PostMapping
    public DetailedPatientDTO post(@RequestBody CreatePatientDTO dto) {
        return this.service.create(dto);
    }
}
