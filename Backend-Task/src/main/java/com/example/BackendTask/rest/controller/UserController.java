package com.example.BackendTask.rest.controller;

import com.example.BackendTask.rest.dto.UserDto;
import com.example.BackendTask.rest.handler.UserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "user", description = "REST API for User")

public class UserController {
    private UserHandler usersHandler;

    @GetMapping
    @Operation(summary = "get all", description = "this API to get all user in pages")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return usersHandler.getAll(page, size);

    }

    @GetMapping("/{id}")
    @Operation(summary = "get User By Id")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return usersHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "add New User")
    public ResponseEntity<?> save(@RequestBody UserDto userDto) {
        return usersHandler.save(userDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update User ")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UserDto userDto) {
        return usersHandler.update(id, userDto);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "delete User By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return usersHandler.delete(id);
    }

    //-------------------------------------Role-----------------------------------------------------------

    @GetMapping("/{id}/role")
    @Operation(summary = "get roles for user")
    public ResponseEntity<?> getRoles(@PathVariable Integer id) {

        return usersHandler.getAllUserRoles(id);
    }


    @PostMapping("/{id}/role/{roleId}")
    @Operation(summary = "add role to user")
    public ResponseEntity<?> saveRole(@PathVariable(name = "id") Integer userId,
                                      @PathVariable(name = "roleId") Integer roleId) {

        return usersHandler.addRoleForUser(userId, roleId);
    }

}