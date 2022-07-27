package co.com.hospital.persistence.repository;

import co.com.hospital.persistence.entities.ClinicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalHistoryRepository extends JpaRepository<ClinicalHistory, String> {
}