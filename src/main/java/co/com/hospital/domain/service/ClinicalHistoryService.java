package co.com.hospital.domain.service;

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

    public ClinicalHistory create(Patient patient) {
        ClinicalHistory clinicalHistory = new ClinicalHistory(List.of(), patient);
        return this.repository.save(clinicalHistory);
    }
}
