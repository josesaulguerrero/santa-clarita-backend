package co.com.hospital.persistence.mapper;

import co.com.hospital.domain.dto.appointment.CreateAppointmentDTO;
import co.com.hospital.domain.dto.appointment.DetailedAppointmentDTO;
import co.com.hospital.domain.dto.appointment.PartialAppointmentDTO;
import co.com.hospital.domain.dto.specialty.PartialSpecialtyDTO;
import co.com.hospital.persistence.entities.Appointment;
import co.com.hospital.persistence.entities.ClinicalHistory;
import co.com.hospital.persistence.entities.Specialty;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentMapper {
    public PartialAppointmentDTO entityToPartialDTO(Appointment appointment) {
        return new PartialAppointmentDTO(appointment.getId(), appointment.getDate());
    }

    public List<PartialAppointmentDTO> entitiesToPartialDTOs(List<Appointment> appointments) {
        return appointments
                .stream()
                .map(this::entityToPartialDTO)
                .toList();
    }

    public DetailedAppointmentDTO entityToDetailedDTO(Appointment appointment) {
        Specialty specialty = appointment.getSpecialtyInCharge();
        PartialSpecialtyDTO specialtyDTO = new PartialSpecialtyDTO(
                specialty.getId(),
                specialty.getName()
        );
        return new DetailedAppointmentDTO(appointment.getId(), appointment.getDate(), specialtyDTO);
    }

    public List<DetailedAppointmentDTO> entitiesToDetailedDTOs(List<Appointment> appointments) {
        return appointments
                .stream()
                .map(this::entityToDetailedDTO)
                .toList();
    }

    public Appointment createDTOToEntity(CreateAppointmentDTO dto) {
        return new Appointment(
                LocalDateTime.now(),
                null,
                new Specialty(dto.getSpecialtyId()));
    }
}
