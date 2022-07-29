package co.com.hospital.domain.service;

import co.com.hospital.domain.dto.appointment.CreateAppointmentDTO;
import co.com.hospital.domain.dto.appointment.DetailedAppointmentDTO;
import co.com.hospital.domain.dto.appointment.PartialAppointmentDTO;
import co.com.hospital.domain.dto.specialty.DetailedSpecialtyDTO;
import co.com.hospital.persistence.entities.Appointment;
import co.com.hospital.persistence.entities.ClinicalHistory;
import co.com.hospital.persistence.entities.Specialist;
import co.com.hospital.persistence.entities.Specialty;
import co.com.hospital.persistence.mapper.AppointmentMapper;
import co.com.hospital.persistence.repository.AppointmentRepository;
import co.com.hospital.utils.HttpExceptionBuilder;
import co.com.hospital.utils.RuntimeExceptionBuilder;
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
    private final AppointmentMapper mapper;
    private final SpecialtyService specialtyService;
    private final ClinicalHistoryService clinicalHistoryService;

    public List<PartialAppointmentDTO> findAll() {
        List<Appointment> appointments = this.repository.findAll();
        return this.mapper.entitiesToPartialDTOs(appointments);
    }

    public List<PartialAppointmentDTO> findAllBySpecialtyId(Long specialtyId) {
        List<Appointment> appointments = this.repository.findAllBySpecialtyInChargeId(specialtyId);
        return this.mapper.entitiesToPartialDTOs(appointments);
    }

    public List<PartialAppointmentDTO> findAllByPatientId(Long specialtyId) {
        List<Appointment> appointments = this.repository.findAllByPatientId(specialtyId);
        return this.mapper.entitiesToPartialDTOs(appointments);
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
        if (!this.specialtyService.exists(dto.getSpecialtyId())) {
            throw new HttpExceptionBuilder()
                    .developerMessage("The given Specialty Id do not belong to any of the entities in the database.")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .build();
        }
        Appointment appointmentFromDTO = this.mapper.createDTOToEntity(dto);
        Long clinicalHistoryId = this.clinicalHistoryService
                .findByPatientId(dto.getPatientId())
                .getId();
        DetailedSpecialtyDTO specialtyDTO = this.specialtyService.findById(dto.getSpecialtyId());
        Specialty asssociatedSpecialty = new Specialty(
                specialtyDTO.getId(),
                specialtyDTO.getName(),
                new Specialist(specialtyDTO.getSpecialistInCharge().getId())
        );
        appointmentFromDTO.setSpecialtyInCharge(asssociatedSpecialty);
        Appointment savedAppointment = this.repository.save(appointmentFromDTO);
        this.clinicalHistoryService.addAppointmentRecord(clinicalHistoryId, savedAppointment);
        return this.mapper.entityToDetailedDTO(savedAppointment);
    }

    public DetailedAppointmentDTO delete(Long id) {
        DetailedAppointmentDTO dto = this.findById(id);
        this.repository.deleteById(dto.getId());
        return dto;
    }
}
