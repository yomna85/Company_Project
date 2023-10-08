package com.example.BackendTask.service;

import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public Page<Employee> getAll(Integer page, Integer size) {
        return employeeRepository.findAll(PageRequest.of(page, size));
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void update(Employee employee) {
        employeeRepository.save(employee);
    }

    public Optional<Employee> getById(Integer id) {
        return employeeRepository.findById(id);
    }


    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    public Optional<Employee> getByEmployeeCode(String employeeCode) {
        return employeeRepository.findByEmployeeCode(employeeCode);
    }

}
