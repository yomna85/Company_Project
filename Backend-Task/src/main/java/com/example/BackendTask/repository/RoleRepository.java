package com.example.BackendTask.repository;

import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
