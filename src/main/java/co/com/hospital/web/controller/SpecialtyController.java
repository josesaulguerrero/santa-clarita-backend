package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.specialist.CreateSpecialistDTO;
import co.com.hospital.domain.dto.specialist.DetailedSpecialistDTO;
import co.com.hospital.domain.dto.specialist.PartialSpecialistDTO;
import co.com.hospital.domain.dto.specialty.CreateAndUpdateSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.DetailedSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.PartialSpecialtyDTO;
import co.com.hospital.domain.service.SpecialtyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("specialties")
@AllArgsConstructor
public class SpecialtyController {
    private final SpecialtyService service;

    @GetMapping
    public List<PartialSpecialtyDTO> getAll() {
        return this.service.findAll();
    }

    @GetMapping("{id}")
    public DetailedSpecialtyDTO getById(@PathVariable("id") Long id) {
        return this.service.findById(id);
    }

    @PostMapping
    public DetailedSpecialtyDTO post(@RequestBody CreateAndUpdateSpecialtyDTO dto) {
        return this.service.create(dto);
    }

    @PutMapping
    public DetailedSpecialtyDTO update(@RequestBody CreateAndUpdateSpecialtyDTO dto) {
        return this.service.update(dto);
    }

    @PutMapping("specialist")
    public DetailedSpecialtyDTO assignNewSpecialist(@RequestBody CreateAndUpdateSpecialtyDTO dto) {
        return this.service.assignSpecialist(dto);
    }

    @DeleteMapping("{id}")
    public DetailedSpecialtyDTO delete(@PathVariable("id") Long id) {
        return this.service.delete(id);
    }
}
