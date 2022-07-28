package co.com.hospital.persistence.repository;

import co.com.hospital.persistence.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    @Query(
            "select count(distinct appointment) from Appointment appointment" +
                    " where appointment.specialtyInCharge.id = ?1"
    )
    Long countAssociatedAppointments(Long specialtyId);
}