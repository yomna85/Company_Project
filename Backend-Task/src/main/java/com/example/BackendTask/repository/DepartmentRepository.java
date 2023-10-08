package com.example.BackendTask.repository;

import com.example.BackendTask.entity.Department;
import com.example.BackendTask.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
