package co.com.hospital.persistence.repository;

import co.com.hospital.persistence.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}