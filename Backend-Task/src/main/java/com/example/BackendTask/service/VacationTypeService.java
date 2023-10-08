package com.example.BackendTask.service;

import com.example.BackendTask.entity.VacationType;
import com.example.BackendTask.repository.VacationTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VacationTypeService {

    private VacationTypeRepository vacationTypeRepository;


    public Optional<VacationType> getById(Integer id) {
        return vacationTypeRepository.findById(id);
    }

    public VacationType save(VacationType vacationType) {
        return vacationTypeRepository.save(vacationType);
    }

    public void update(VacationType vacationType) {vacationTypeRepository.save(vacationType);
    }

    public void delete(VacationType vacationType) {
        vacationTypeRepository.delete(vacationType);
    }

    public Page<VacationType> getAll(Integer page, Integer size) {
        return vacationTypeRepository.findAll(PageRequest.of(page, size));
    }
}


