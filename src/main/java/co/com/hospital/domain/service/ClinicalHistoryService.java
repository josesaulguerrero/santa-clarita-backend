package co.com.hospital.domain.service;

import co.com.hospital.persistence.entities.Appointment;
import co.com.hospital.persistence.entities.ClinicalHistory;
import co.com.hospital.persistence.entities.Patient;
import co.com.hospital.persistence.repository.ClinicalHistoryRepository;
import co.com.hospital.utils.RuntimeExceptionBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ClinicalHistoryService {
    private final ClinicalHistoryRepository repository;

    public ClinicalHistory findById(String id) {
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeExceptionBuilder(IllegalArgumentException.class)
                            .developerMessage("The given id doesn't belong to any Clinical History.")
                            .build()
                );
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

    public Boolean exists(String id) {
        return this.repository.existsById(id);
    }

    public ClinicalHistory create(Patient patient, List<Appointment> appointments) {
        ClinicalHistory entity = new ClinicalHistory(appointments, patient);
        return this.repository.save(entity);
    }

    public ClinicalHistory create(Patient patient) {
        return this.create(patient, List.of());
    }

    public ClinicalHistory addAppointmentRecord(String clinicalHistoryId, Appointment appointment) {
        ClinicalHistory entity = this.findById(clinicalHistoryId);
        entity.addAppointment(appointment);
        return this.repository.save(entity);
    }
}
