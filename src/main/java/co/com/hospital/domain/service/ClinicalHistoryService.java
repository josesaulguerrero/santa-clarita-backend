package co.com.hospital.domain.service;

import co.com.hospital.persistence.entities.Appointment;
import co.com.hospital.persistence.entities.ClinicalHistory;
import co.com.hospital.persistence.entities.Patient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClinicalHistoryService {
    public ClinicalHistory findById(Long id) {

    }

    public ClinicalHistory findByPatientId(Long patientId) {

    }

    public ClinicalHistory create(Patient patient, List<Appointment> appointments) {

    }

    public ClinicalHistory create(Patient patient) {
        return this.create(patient, List.of());
    }

    public ClinicalHistory addAppointmentRecord(Appointment appointment) {

    }
}
