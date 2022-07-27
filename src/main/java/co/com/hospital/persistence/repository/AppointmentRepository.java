package co.com.hospital.persistence.repository;

import co.com.hospital.persistence.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}