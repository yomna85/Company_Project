package com.example.BackendTask.repository;

import com.example.BackendTask.entity.Department;
import com.example.BackendTask.entity.VacationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationTypeRepository  extends JpaRepository<VacationType,Integer> {
}
