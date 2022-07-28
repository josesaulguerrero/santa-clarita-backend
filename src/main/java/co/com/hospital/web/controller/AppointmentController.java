package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.appointment.CreateAppointmentDTO;
import co.com.hospital.domain.dto.appointment.DetailedAppointmentDTO;
import co.com.hospital.domain.dto.appointment.PartialAppointmentDTO;
import co.com.hospital.domain.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("appointments")
public class AppointmentController {
    private final AppointmentService service;

    @GetMapping
    public List<PartialAppointmentDTO> getAll() {
        return this.service.findAll();
    }

    @GetMapping
    public List<PartialAppointmentDTO> getAllBySpecialtyId(Long specialtyId) {
        return this.service.findAllBySpecialtyId(specialtyId);
    }

    @GetMapping("{id}")
    public DetailedAppointmentDTO getById(@PathVariable("id") Long id) {
        return this.service.findById(id);
    }

    @PostMapping
    public DetailedAppointmentDTO post(@RequestBody CreateAppointmentDTO dto) {
        return this.service.create(dto);
    }

    @DeleteMapping("{id}")
    public DetailedAppointmentDTO delete(@PathVariable("id") Long id) {
        return this.service.delete(id);
    }
}
