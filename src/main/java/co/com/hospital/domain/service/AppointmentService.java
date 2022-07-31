package co.com.hospital.domain.service;

import co.com.hospital.domain.dto.appointment.CreateAppointmentDTO;
import co.com.hospital.persistence.entities.Appointment;
import co.com.hospital.persistence.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AppointmentService {
    private final AppointmentRepository repository;
    private final SpecialtyService specialtyService;
    private final ClinicalHistoryService clinicalHistoryService;

    public List<Appointment> findAll() {
    }

    public List<Appointment> findAllBySpecialtyId(Long specialtyId) {
    }

    public List<Appointment> findAllByPatientId(Long specialtyId) {
    }

    public Appointment findById(Long id) {
    }

    public Appointment create(Appointment entity) {
    }

    public Appointment delete(Long id) {
    }
}
