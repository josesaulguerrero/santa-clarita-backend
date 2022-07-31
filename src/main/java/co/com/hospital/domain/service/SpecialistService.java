package co.com.hospital.domain.service;

import co.com.hospital.persistence.entities.Specialist;
import co.com.hospital.persistence.entities.Specialty;
import co.com.hospital.persistence.repository.SpecialistRepository;
import lombok.AllArgsConstructor;
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
    }

    public Specialist create(Specialist entity) {
    }

    private Specialist update(Specialist specialist) {
        return this.repository.save(specialist);
    }

    public Specialist assignToSpeciality(Long specialistId, Specialty specialty) {
    }

    public void removeFromSpecialty(Long specialistId) {
    }

    public Specialist delete(Long id) {
    }
}
