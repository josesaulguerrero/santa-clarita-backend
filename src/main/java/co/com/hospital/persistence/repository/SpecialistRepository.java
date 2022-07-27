package co.com.hospital.persistence.repository;

import co.com.hospital.persistence.entities.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
}