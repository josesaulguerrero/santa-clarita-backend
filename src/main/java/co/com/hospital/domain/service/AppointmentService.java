package co.com.hospital.domain.service;

import co.com.hospital.domain.dto.appointment.CreateAppointmentDTO;
import co.com.hospital.domain.dto.appointment.DetailedAppointmentDTO;
import co.com.hospital.domain.dto.appointment.PartialAppointmentDTO;
import co.com.hospital.persistence.entities.Appointment;
import co.com.hospital.persistence.entities.ClinicalHistory;
import co.com.hospital.persistence.entities.Specialty;
import co.com.hospital.persistence.mapper.AppointmentMapper;
import co.com.hospital.persistence.repository.AppointmentRepository;
import co.com.hospital.utils.RuntimeExceptionBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AppointmentService {
    private final AppointmentRepository repository;
    private final AppointmentMapper mapper;

    public List<PartialAppointmentDTO> findAll() {
        List<Appointment> appointments = this.repository.findAll();
        return this.mapper.entitiesToPartialDTOs(appointments);
    }

    public List<PartialAppointmentDTO> findAllByDepartmentId(Long specialtyId) {
        List<Appointment> appointments = this.repository.findAllBySpecialtyInChargeId(specialtyId);
        return this.mapper.entitiesToPartialDTOs(appointments);
    }

    public Long getCountBySpecialtyId(Long specialtyId) {
        return this.repository.countBySpecialtyInChargeId(specialtyId);
    }

    public DetailedAppointmentDTO findById(Long id) {
        Appointment entity = this.repository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeExceptionBuilder(IllegalArgumentException.class)
                                .developerMessage("The given id doesn't belong to any Appointment.")
                                .build()
                );
        return this.mapper.entityToDetailedDTO(entity);
    }

    public DetailedAppointmentDTO create(CreateAppointmentDTO dto) {
        Appointment entityFromDTO = this.mapper.createDTOToEntity(dto);
        //TODO the dto brings the ids of the clinical history and specialty, so we gotta retrieve them from their corresponding services.
        ClinicalHistory associatedClinicalHistory = null;
        Specialty asssociatedSpecialty = null;
        entityFromDTO.setAssociatedClinicalHistory(associatedClinicalHistory);
        entityFromDTO.setSpecialtyInCharge(asssociatedSpecialty);
        Appointment savedEntity = this.repository.save(entityFromDTO);
        return this.mapper.entityToDetailedDTO(savedEntity);
    }

    public DetailedAppointmentDTO delete(Long id) {
        DetailedAppointmentDTO dto = this.findById(id);
        this.repository.deleteById(dto.getId());
        return dto;
    }
}
