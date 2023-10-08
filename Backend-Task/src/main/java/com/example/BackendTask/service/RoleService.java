package com.example.BackendTask.service;

import com.example.BackendTask.entity.Role;
import com.example.BackendTask.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    public Page<Role> getAll(Integer page, Integer size) {
        return roleRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Role> getById(Integer id) {
        return roleRepository.findById(id);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }
}