package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.appointment.CreateAppointmentDTO;
import co.com.hospital.domain.dto.appointment.DetailedAppointmentDTO;
import co.com.hospital.domain.dto.appointment.PartialAppointmentDTO;
import co.com.hospital.domain.service.AppointmentService;
import co.com.hospital.persistence.entities.Appointment;
import co.com.hospital.web.mapper.AppointmentMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("appointments")
@CrossOrigin("*")
public class AppointmentController {
    private final AppointmentService service;
    private final AppointmentMapper mapper;

    @GetMapping
    public ResponseEntity<List<PartialAppointmentDTO>> getAll() {
        List<PartialAppointmentDTO> mappedAppointments = mapper.entitiesToPartialDTOs(this.service.findAll());
        return new ResponseEntity<>(mappedAppointments, HttpStatus.OK);
    }

    @GetMapping("specialty/{specialtyId}")
    public ResponseEntity<List<PartialAppointmentDTO>> getAllBySpecialtyId(@PathVariable("specialtyId") Long specialtyId) {
        List<PartialAppointmentDTO> mappedAppointments = this.mapper.entitiesToPartialDTOs(
                this.service.findAllBySpecialtyId(specialtyId)
        );
        return new ResponseEntity<>(mappedAppointments, HttpStatus.OK);
    }

    @GetMapping("patient/{patientId}")
    public ResponseEntity<List<PartialAppointmentDTO>> getAllByPatientId(@PathVariable("patientId") Long patientId) {
        List<PartialAppointmentDTO> mappedAppointments = this.mapper.entitiesToPartialDTOs(
                this.service.findAllByPatientId(patientId)
        );
        return new ResponseEntity<>(mappedAppointments, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailedAppointmentDTO> getById(@PathVariable("id") Long id) {
        DetailedAppointmentDTO mappedAppointment = this.mapper.entityToDetailedDTO(this.service.findById(id));
        return new ResponseEntity<>(mappedAppointment, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DetailedAppointmentDTO> post(@RequestBody CreateAppointmentDTO dto) {
        Appointment entity = this.service.create(this.mapper.createDTOToEntity(dto));
        return new ResponseEntity<>(this.mapper.entityToDetailedDTO(entity), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DetailedAppointmentDTO> delete(@PathVariable("id") Long id) {
        DetailedAppointmentDTO mappedAppointment = this.mapper.entityToDetailedDTO(this.service.delete(id));
        return new ResponseEntity<>(mappedAppointment, HttpStatus.OK);
    }
}
