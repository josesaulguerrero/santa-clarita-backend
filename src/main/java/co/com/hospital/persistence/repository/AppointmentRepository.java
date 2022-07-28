package co.com.hospital.persistence.repository;

import co.com.hospital.persistence.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(
            "select appointment from Appointment appointment where appointment.specialtyInCharge.id = ?1"
    )
    List<Appointment> findAllBySpecialtyInChargeId(Long id);
}