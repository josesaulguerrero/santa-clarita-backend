package co.com.hospital.domain.service;

import co.com.hospital.domain.dto.specialty.CreateAndUpdateSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.DetailedSpecialtyDTO;
import co.com.hospital.domain.dto.specialty.PartialSpecialtyDTO;
import co.com.hospital.persistence.entities.Specialty;
import co.com.hospital.persistence.mapper.SpecialtyMapper;
import co.com.hospital.persistence.repository.SpecialtyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SpecialtyService {
    private final SpecialtyRepository repository;
    private final SpecialtyMapper mapper;

    public List<PartialSpecialtyDTO> findAll() {

    }

    public DetailedSpecialtyDTO findById(Long id) {

    }

    public DetailedSpecialtyDTO create(CreateAndUpdateSpecialtyDTO dto) {

    }

    public DetailedSpecialtyDTO update(CreateAndUpdateSpecialtyDTO dto) {

    }

    public DetailedSpecialtyDTO assignSpecialist() {

    }

    public DetailedSpecialtyDTO delete(Long id) {

    }
}
