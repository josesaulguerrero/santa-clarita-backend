package co.com.hospital.domain.service;

import co.com.hospital.domain.dto.appointment.CreateAppointmentDTO;
import co.com.hospital.persistence.entities.Appointment;
import co.com.hospital.persistence.entities.ClinicalHistory;
import co.com.hospital.persistence.entities.Specialty;
import co.com.hospital.persistence.repository.AppointmentRepository;
import co.com.hospital.utils.HttpExceptionBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return this.repository.findAll();
    }

    public List<Appointment> findAllBySpecialtyId(Long specialtyId) {
        return this.repository.findAllBySpecialtyInChargeId(specialtyId);
    }

    public List<Appointment> findAllByPatientId(Long patientId) {
        return this.repository.findAllByPatientId(patientId);
    }

    public Appointment findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() ->
                        new HttpExceptionBuilder()
                                .developerMessage("The given id does not belong to any of the registered Appointments in this system.")
                                .statusCode(HttpStatus.NOT_FOUND)
                                .build()
                );
    }

    public Appointment create(Appointment entity) {
        Long specialtyId = entity.getSpecialtyInCharge().getId();
        Specialty takenAt = this.specialtyService.findById(specialtyId);
        Long clinicalHistoryId = entity.getAssociatedClinicalHistory().getId();
        ClinicalHistory clinicalHistory = this.clinicalHistoryService.findById(clinicalHistoryId);
        Appointment appointment = new Appointment(entity.getDate(), clinicalHistory, takenAt);
        this.clinicalHistoryService.addAppointmentRecord(clinicalHistoryId, appointment);
        return appointment;
    }

    public Appointment delete(Long id) {
        Appointment appointmentToBeDeleted = this.findById(id);
        this.repository.delete(appointmentToBeDeleted);
        return appointmentToBeDeleted;
    }
}
