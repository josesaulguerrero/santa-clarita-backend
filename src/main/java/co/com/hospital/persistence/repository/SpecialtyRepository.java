package co.com.hospital.persistence.repository;

import co.com.hospital.persistence.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
}