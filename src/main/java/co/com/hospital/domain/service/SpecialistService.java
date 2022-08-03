package co.com.hospital.domain.service;

import co.com.hospital.domain.dto.specialist.CreateSpecialistDTO;
import co.com.hospital.persistence.entities.Specialist;
import co.com.hospital.persistence.entities.Specialty;
import co.com.hospital.persistence.repository.SpecialistRepository;
import co.com.hospital.utils.HttpExceptionBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SpecialistService {
    private final SpecialistRepository repository;

    public List<Specialist> findAll() {
        return this.repository.findAll();
    }

    public List<Specialist> findAvailable() {
        return this.repository.findAllByIsAvailableTrue();
    }

    public Specialist findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() ->
                        new HttpExceptionBuilder()
                                .developerMessage("The given id does not belong to any of our registered Specialists")
                                .statusCode(HttpStatus.NOT_FOUND)
                                .build()
                );
    }

    public Specialist create(CreateSpecialistDTO dto) {
        Specialist specialist = new Specialist(
                dto.getDni(),
                dto.getFullName(),
                dto.getAge(),
                true, // specialists are available by default.
                null
        );
        return this.repository.save(specialist);
    }
    public Specialist assignToSpeciality(Long specialistId, Specialty specialty) {
        Specialist specialist = this.findById(specialistId);
        specialist.setAssociatedSpecialty(specialty);
        specialist.setIsAvailable(false);
        return this.repository.save(specialist);
    }

    public void removeFromSpecialty(Long specialistId) {
        Specialist specialist = this.findById(specialistId);
        specialist.setAssociatedSpecialty(null);
        specialist.setIsAvailable(true);
        this.repository.save(specialist);
    }

    private boolean specialistCanBeDeleted(Specialist specialist) {
        return specialist.getAssociatedSpecialty() == null && specialist.getIsAvailable();
    }

    public Specialist delete(Long id) {
        Specialist specialistToBeDeleted = this.findById(id);
        if (!this.specialistCanBeDeleted(specialistToBeDeleted)) {
            throw new HttpExceptionBuilder()
                    .developerMessage("The specialist is associated to a Specialty; You cannot delete it.")
                    .statusCode(HttpStatus.FORBIDDEN)
                    .build();
        }
        this.repository.delete(specialistToBeDeleted);
        return specialistToBeDeleted;
    }
}
