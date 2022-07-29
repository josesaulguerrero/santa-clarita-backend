package co.com.hospital.web.controller;

import co.com.hospital.domain.dto.appointment.CreateAppointmentDTO;
import co.com.hospital.domain.dto.appointment.DetailedAppointmentDTO;
import co.com.hospital.domain.dto.appointment.PartialAppointmentDTO;
import co.com.hospital.domain.service.AppointmentService;
import co.com.hospital.utils.HttpException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("appointments")
public class AppointmentController {
    private final AppointmentService service;

    @GetMapping
    public ResponseEntity<List<PartialAppointmentDTO>> getAll() {
        try {
            return new ResponseEntity<>(this.service.findAll(), HttpStatus.OK);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("specialty/{specialtyId}")
    public ResponseEntity<List<PartialAppointmentDTO>> getAllBySpecialtyId(@PathVariable("specialtyId") Long specialtyId) {
        try {
            return new ResponseEntity<>(this.service.findAllBySpecialtyId(specialtyId), HttpStatus.OK);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("patient/{patientId}")
    public ResponseEntity<List<PartialAppointmentDTO>> getAllByPatientId(@PathVariable("patientId") Long patientId) {
        try {
            return new ResponseEntity<>(this.service.findAllByPatientId(patientId), HttpStatus.OK);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailedAppointmentDTO> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(this.service.findById(id), HttpStatus.OK);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @PostMapping
    public ResponseEntity<DetailedAppointmentDTO> post(@RequestBody CreateAppointmentDTO dto) {
        try {
            return new ResponseEntity<>(this.service.create(dto), HttpStatus.CREATED);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DetailedAppointmentDTO> delete(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
        } catch (HttpException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}
