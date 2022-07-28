package co.com.hospital.persistence.repository;

import co.com.hospital.persistence.entities.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
    List<Specialist> findAllByIsAvailableTrue();
}