package co.com.hospital.domain.service;

import co.com.hospital.domain.dto.patient.CreatePatientDTO;
import co.com.hospital.persistence.entities.ClinicalHistory;
import co.com.hospital.persistence.entities.Patient;
import co.com.hospital.persistence.repository.PatientRepository;
import co.com.hospital.utils.HttpExceptionBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PatientService {
    private final PatientRepository repository;
    private final ClinicalHistoryService clinicalHistoryService;

    public List<Patient> findAll() {
        return this.repository.findAll();
    }

    public Patient findById(Long id) {
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> new HttpExceptionBuilder()
                                .developerMessage("The given id does not belong to any Patient.")
                                .statusCode(HttpStatus.NOT_FOUND)
                                .build()
                );
    }

    public Patient create(CreatePatientDTO dto) {
        Patient patient = new Patient(dto.getDni(), dto.getFullName(), dto.getAge(), null);
        ClinicalHistory clinicalHistory = this.clinicalHistoryService.create(patient);
        patient.setClinicalHistory(clinicalHistory);
        return this.repository.save(patient);
    }
}
