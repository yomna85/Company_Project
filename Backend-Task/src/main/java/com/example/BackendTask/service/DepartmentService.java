package com.example.BackendTask.service;

import com.example.BackendTask.entity.Department;
import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    public Optional<Department> getById(Integer id) {
        return departmentRepository.findById(id);
    }

    public Department save(Department department) {

        return departmentRepository.save(department);
    }
    public void update(Department department) {

        departmentRepository.save(department);
    }

    public void delete(Department department) {
        departmentRepository.delete(department);
    }
}
