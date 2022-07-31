package co.com.hospital.domain.service;

import co.com.hospital.persistence.entities.Appointment;
import co.com.hospital.persistence.entities.ClinicalHistory;
import co.com.hospital.persistence.entities.Patient;
import co.com.hospital.persistence.repository.ClinicalHistoryRepository;
import co.com.hospital.utils.HttpExceptionBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ClinicalHistoryService {
    private final ClinicalHistoryRepository repository;

    public ClinicalHistory findById(Long id) {
        return this.repository
                .findById(id)
                .orElseThrow(() ->
                        new HttpExceptionBuilder()
                                .developerMessage("The given id doesn't belong to any Clinical History.")
                                .statusCode(HttpStatus.NOT_FOUND)
                                .build()
                );
    }

    public ClinicalHistory findByPatientId(Long patientId) {
        return this.repository
                .findByAssociatedPatientId(patientId)
                .orElseThrow(
                        () -> new HttpExceptionBuilder()
                                .developerMessage("The given patient id doesn't belong to any Clinical History.")
                                .statusCode(HttpStatus.NOT_FOUND)
                                .build()
                );
    }

    public Boolean exists(Long id) {
        return this.repository.existsById(id);
    }

    public ClinicalHistory create() {
        ClinicalHistory clinicalHistory = new ClinicalHistory(List.of(), null);
        return this.repository.save(clinicalHistory);
    }

    public ClinicalHistory assignToPatient(Long clinicalHistoryId, Patient patient) {
        ClinicalHistory clinicalHistory = this.findById(clinicalHistoryId);
        clinicalHistory.setAssociatedPatient(patient);
        return clinicalHistory;
    }

    public void addAppointmentRecord(Long clinicalHistoryId, Appointment appointment) {
        ClinicalHistory clinicalHistory = this.findById(clinicalHistoryId);
        clinicalHistory.addAppointment(appointment);
        this.repository.save(clinicalHistory);
    }
}
