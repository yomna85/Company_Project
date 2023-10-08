package com.example.BackendTask.service;

import com.example.BackendTask.entity.VacationLeave;
import com.example.BackendTask.repository.VacationLeaveRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VacationLeaveService {
    private VacationLeaveRepository vacationLeaveRepository;

    public Page<VacationLeave> getAllByEmployee(Integer employeeId, Integer page, Integer size) {
        return vacationLeaveRepository.findAllByEmployee(employeeId, PageRequest.of(page, size));
    }

    public Optional<VacationLeave> getById(Integer id) {
        return vacationLeaveRepository.findById(id);
    }

    public VacationLeave save(VacationLeave vacationLeave) {
        return vacationLeaveRepository.save(vacationLeave);
    }

    public VacationLeave update(VacationLeave vacationLeave) {
        return vacationLeaveRepository.save(vacationLeave);
    }

    public void delete(VacationLeave vacationLeave) {
        vacationLeaveRepository.delete(vacationLeave);
    }
}