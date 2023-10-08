package com.example.BackendTask.service;

import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.entity.User;
import com.example.BackendTask.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public Page<User> getAll(Integer page, Integer size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> findRolesById(Integer id) {
        return userRepository.findRolesById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findRolesByUserName(String username) {
        return userRepository.findRolesByUserName(username);
    }
}
