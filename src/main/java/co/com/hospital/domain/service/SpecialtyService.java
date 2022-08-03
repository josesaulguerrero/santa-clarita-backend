package co.com.hospital.domain.service;

import co.com.hospital.domain.dto.specialty.CreateAndUpdateSpecialtyDTO;
import co.com.hospital.persistence.entities.Specialist;
import co.com.hospital.persistence.entities.Specialty;
import co.com.hospital.persistence.repository.SpecialtyRepository;
import co.com.hospital.utils.HttpExceptionBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SpecialtyService {
    private final SpecialtyRepository repository;
    private final SpecialistService specialistService;


    public List<Specialty> findAll() {
        return this.repository.findAll();
    }

    public Specialty findById(Long id) {
        return this.repository
                .findById(id)
                .orElseThrow(
                        () -> new HttpExceptionBuilder()
                                .developerMessage("The given id doesn't belong to any Specialty in these facilities.")
                                .statusCode(HttpStatus.NOT_FOUND)
                                .build()
                );
    }

    public Boolean exists(Long id) {
        return this.repository.existsById(id);
    }

    public Specialty create(CreateAndUpdateSpecialtyDTO dto) {
        Long assignedSpecialistId = dto.getSpecialistId();
        Specialty specialty = new Specialty(dto.getName(), null);
        Specialist assignedSpecialist = this.specialistService.assignToSpeciality(assignedSpecialistId, specialty);
        specialty.setSpecialistInCharge(assignedSpecialist);
        return this.repository.save(specialty);
    }

    public Specialty update(CreateAndUpdateSpecialtyDTO dto) {
        Specialty specialty = this.findById(dto.getId());
        if (!dto.getSpecialistId().equals(specialty.getSpecialistInCharge().getId())) {
            this.specialistService.removeFromSpecialty(specialty.getSpecialistInCharge().getId());
            this.specialistService.assignToSpeciality(dto.getSpecialistId(), specialty);
        }
        specialty.setName(dto.getName());
        return specialty;
    }

    private boolean specialtyCanBeDeleted(Long specialtyId) {
        return this.repository.countAssociatedAppointments(specialtyId) == 0;
    }

    public Specialty delete(Long id) {
        Specialty specialtyToBeDeleted = this.findById(id);
        if (!this.specialtyCanBeDeleted(specialtyToBeDeleted.getId())) {
            throw new HttpExceptionBuilder()
                    .developerMessage("This specialty can't be deleted; it has appointments assigned.")
                    .statusCode(HttpStatus.FORBIDDEN)
                    .build();
        }
        Long associatedSpecialistId = specialtyToBeDeleted.getSpecialistInCharge().getId();
        this.specialistService.removeFromSpecialty(associatedSpecialistId);
        this.repository.delete(specialtyToBeDeleted);
        return specialtyToBeDeleted;
    }
}
