package co.com.hospital.domain.service;

import co.com.hospital.domain.dto.specialty.DetailedSpecialtyDTO;
import co.com.hospital.persistence.entities.Specialty;
import co.com.hospital.utils.HttpException;
import co.com.hospital.persistence.repository.SpecialtyRepository;
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
                        () -> new HttpException("The given id doesn't belong to any Specialty in these facilities.", HttpStatus.NOT_FOUND)
                );
    }

    public Boolean exists(Long id) {
        return this.repository.existsById(id);
    }

    public Specialty create(Specialty entity) {
    }

    public Specialty update(Specialty entity) {
    }

    public Specialty assignSpecialist(Long specialtyId, Long specialistId) {
    }

    public Specialty delete(Long id) {
    }
}
