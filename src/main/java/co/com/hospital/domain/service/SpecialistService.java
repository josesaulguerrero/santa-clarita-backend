package co.com.hospital.domain.service;

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

    public Specialist create(Specialist entity) {
        entity.setAssociatedSpecialty(null);
        entity.setIsAvailable(true);
        return this.repository.save(entity);
    }

    private Specialist update(Specialist specialist) {
        return this.repository.save(specialist);
    }

    public Specialist assignToSpeciality(Long specialistId, Specialty specialty) {
        Specialist specialist = this.findById(specialistId);
        specialist.setAssociatedSpecialty(specialty);
        specialist.setIsAvailable(false);
        return this.update(specialist);
    }

    public void removeFromSpecialty(Long specialistId) {
        Specialist specialist = this.findById(specialistId);
        specialist.setAssociatedSpecialty(null);
        specialist.setIsAvailable(true);
        this.update(specialist);
    }

    public Specialist delete(Long id) {
        Specialist specialistToBeDeleted = this.findById(id);
        this.repository.delete(specialistToBeDeleted);
        return specialistToBeDeleted;
    }
}
