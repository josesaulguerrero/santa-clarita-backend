package co.com.hospital.domain.service;

import co.com.hospital.domain.dto.patient.CreatePatientDTO;
import co.com.hospital.domain.dto.patient.DetailedPatientDTO;
import co.com.hospital.domain.dto.patient.PartialPatientDTO;
import co.com.hospital.persistence.entities.ClinicalHistory;
import co.com.hospital.persistence.entities.Patient;
import co.com.hospital.persistence.mapper.PatientMapper;
import co.com.hospital.persistence.repository.PatientRepository;
import co.com.hospital.utils.HttpExceptionBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PatientService {
    private final PatientRepository repository;
    private final PatientMapper mapper;
    private final ClinicalHistoryService clinicalHistoryService;

    public List<PartialPatientDTO> findAll() {
        List<Patient> entities = this.repository.findAll();
        return this.mapper.entitiesToPartialDTOs(entities);
    }
    public DetailedPatientDTO findById(Long id) {
        Patient entity = this.repository
                .findById(id)
                .orElseThrow(
                        () -> new HttpExceptionBuilder()
                                .developerMessage("The given id does not belong to any Patient.")
                                .build()
                );
        return mapper.entityToDetailedDTO(entity);
    }
    public DetailedPatientDTO create(CreatePatientDTO dto){
        Patient patientFromDTO = this.mapper.createDTOToEntity(dto);
        Patient savedPatient = this.repository.save(patientFromDTO);
        ClinicalHistory clinicalHistory = this.clinicalHistoryService.create();
        this.clinicalHistoryService.assignPatient(clinicalHistory.getId(), savedPatient);
        return this.mapper.entityToDetailedDTO(savedPatient);
    }
}
