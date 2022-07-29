package co.com.hospital.persistence.repository;

import co.com.hospital.persistence.entities.ClinicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClinicalHistoryRepository extends JpaRepository<ClinicalHistory, Long> {
    @Query(
            "select history from ClinicalHistory history where history.associatedPatient.id = ?1"
    )
    Optional<ClinicalHistory> findByAssociatedPatientId(Long id);
}