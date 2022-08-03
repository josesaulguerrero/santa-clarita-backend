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
        Long assignedSpecialistId = entity.getSpecialistInCharge().getId();
        Specialist assignedSpecialist = this.specialistService.findById(assignedSpecialistId);
        Specialty specialty = this.repository.save(entity);
        assignedSpecialist = this.specialistService.assignToSpeciality(assignedSpecialist.getId(), specialty);
        specialty.setSpecialistInCharge(assignedSpecialist);
        return specialty;
    }

    private boolean isValidUpdate(Specialty suppliedEntity, Specialty entityFromDB) {
        return suppliedEntity.getSpecialistInCharge() == null || !suppliedEntity.getSpecialistInCharge().equals(entityFromDB.getSpecialistInCharge());
    }

    public Specialty update(CreateAndUpdateSpecialtyDTO dto) {
        Specialty specialty = this.findById(entity.getId());
        if (isValidUpdate(entity, specialty)) {
            throw new HttpExceptionBuilder()
                    .developerMessage("You cannot re-assign a Specialty's specialist directly.")
                    .statusCode(HttpStatus.FORBIDDEN)
                    .build();
        }
        return this.repository.save(entity);
    }

    public Specialty assignSpecialist(Long specialtyId, Long specialistId) {
        Specialty specialty = this.findById(specialtyId);
        Long previousSpecialistId = specialty.getSpecialistInCharge().getId();
        Specialist previousSpecialist = this.specialistService.findById(previousSpecialistId);
        this.specialistService.removeFromSpecialty(previousSpecialist.getId());
        Specialist newSpecialist = this.specialistService.findById(specialistId);
        newSpecialist = this.specialistService.assignToSpeciality(newSpecialist.getId(), specialty);
        specialty.setSpecialistInCharge(newSpecialist);
        return this.repository.save(specialty);
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
        this.repository.delete(specialtyToBeDeleted);
        return specialtyToBeDeleted;
    }
}
