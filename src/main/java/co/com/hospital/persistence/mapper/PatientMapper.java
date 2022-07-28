package co.com.hospital.persistence.mapper;

import co.com.hospital.domain.dto.appointment.PartialAppointmentDTO;
import co.com.hospital.domain.dto.patient.CreatePatientDTO;
import co.com.hospital.domain.dto.patient.DetailedPatientDTO;
import co.com.hospital.domain.dto.patient.PartialPatientDTO;
import co.com.hospital.persistence.entities.ClinicalHistory;
import co.com.hospital.persistence.entities.Patient;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PatientMapper {
    private AppointmentMapper appointmentMapper;

    public PartialPatientDTO entityToPartialDTO(Patient entity) {
        return new PartialPatientDTO(
                entity.getId(),
                entity.getDNI(),
                entity.getFullName(),
                entity.getAge()
        );
    }

    public List<PartialPatientDTO> entitiesToPartialDTOs(List<Patient> entities) {
        return entities
                .stream()
                .map(this::entityToPartialDTO)
                .collect(Collectors.toList());
    }

    public DetailedPatientDTO entityToDetailedDTO(Patient entity) {
        List<PartialAppointmentDTO> appointments = appointmentMapper.entitiesToPartialDTOs(
                entity.getClinicalHistory().getAppointments()
        );
        return new DetailedPatientDTO(
                entity.getId(),
                entity.getDNI(),
                entity.getFullName(),
                entity.getAge(),
                appointments
        );
    }

    public List<DetailedPatientDTO> entitiesToDetailedDTOs(List<Patient> entities) {
        return entities
                .stream()
                .map(this::entityToDetailedDTO)
                .collect(Collectors.toList());
    }

    public Patient createDTOToEntity(CreatePatientDTO dto) {
        // TODO populate the entity clinical hisotry on the service.
        return new Patient(dto.getDni(), dto.getFullName(), dto.getAge(), null);
    }
}
