package com.example.BackendTask.rest.controller;

import com.example.BackendTask.rest.dto.EmployeeDto;
import com.example.BackendTask.rest.dto.RoleDto;
import com.example.BackendTask.rest.handler.RoleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
@Tag(name = "role", description = "REST API for role")
public class RoleController {
    private RoleHandler roleHandler;

    @PostMapping
    @Operation(summary = "add role", description = "this API to add new role")
    public ResponseEntity<?> save(@RequestBody RoleDto roleDto) {
        return roleHandler.save(roleDto);
    }

    @GetMapping
    @Operation(summary = "get All roles ")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return roleHandler.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get role By Id")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {
        return roleHandler.getById(id);
    }

}

