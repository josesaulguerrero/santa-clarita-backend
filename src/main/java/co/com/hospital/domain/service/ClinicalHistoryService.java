package co.com.hospital.domain.service;

import co.com.hospital.persistence.entities.Appointment;
import co.com.hospital.persistence.entities.ClinicalHistory;
import co.com.hospital.persistence.entities.Patient;
import co.com.hospital.persistence.repository.ClinicalHistoryRepository;
import co.com.hospital.utils.RuntimeExceptionBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ClinicalHistoryService {
    private final ClinicalHistoryRepository repository;

    public ClinicalHistory findById(Long id) {
    }

    public ClinicalHistory findByPatientId(Long patientId) {
        return this.repository
                .findByAssociatedPatientId(patientId)
                .orElseThrow(
                        () -> new RuntimeExceptionBuilder(IllegalArgumentException.class)
                                .developerMessage("The given patient id doesn't belong to any Clinical History.")
                                .build()
                );
    }

    public Boolean exists(Long id) {
        return this.repository.existsById(id);
    }

    public ClinicalHistory create() {
    }

    public void assignPatient(Long clinicalHistoryId, Patient patient) {
    }

    public ClinicalHistory addAppointmentRecord(Long clinicalHistoryId, Appointment appointment) {
    }
}
