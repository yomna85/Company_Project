package com.example.BackendTask.rest.handler;

import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.entity.Role;
import com.example.BackendTask.entity.User;
import com.example.BackendTask.rest.dto.EmployeeDto;
import com.example.BackendTask.rest.dto.RoleDto;
import com.example.BackendTask.rest.dto.UserDto;
import com.example.BackendTask.rest.dto.common.PaginatedResultDto;
import com.example.BackendTask.rest.entitymapper.UserMapper;
import com.example.BackendTask.rest.entitymapper.common.PaginationMapper;
import com.example.BackendTask.rest.exception.ErrorCodes;
import com.example.BackendTask.rest.exception.ResourceAlreadyExistsException;
import com.example.BackendTask.rest.exception.ResourceNotFoundException;
import com.example.BackendTask.rest.exception.ResourceRelatedException;
import com.example.BackendTask.service.RoleService;
import com.example.BackendTask.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class UserHandler {
    private UserService userService;
    private UserMapper userMapper;
    private PaginationMapper paginationMapper;
    private RoleService roleService;

    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<User> usersPage = userService.getAll(page, size);
        List<UserDto> usersDtoList = userMapper.toDto(usersPage.getContent());
        PaginatedResultDto<UserDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(usersDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(usersPage));
        return ResponseEntity.ok(paginatedResult);
    }


    public ResponseEntity<?> getById(Integer id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    public ResponseEntity<?> save(UserDto dto) {
        User newUser = userMapper.toEntity(dto);
        userService.save(newUser);
        UserDto userDto = userMapper.toDto(newUser);
        return ResponseEntity.created(URI.create("/user/" + userDto.getId())).body(userDto);
    }

    public ResponseEntity<?> update(Integer id, UserDto userDto) {
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
        User entity = userMapper.updateEntityFromDto(userDto, user);
        userService.save(entity);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> delete(Integer id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
        try {
            userService.delete(user);
        } catch (Exception exception) {
            throw new ResourceRelatedException(User.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE);
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> getAllUserRoles(Integer userId) {
        User user = userService
                .findRolesById(userId).orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), userId));
        UserDto dto = userMapper.toDto(user);
        List<RoleDto> roleDtoList = new ArrayList<>(dto.getRoles());
        Page<RoleDto> rolePage = new PageImpl<>(roleDtoList);
        PaginatedResultDto<RoleDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(roleDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(rolePage));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> addRoleForUser(Integer userId, Integer roleId) {
        User user = userService
                .findRolesById(userId).orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), userId));
        Role role = roleService.getById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), roleId));
        boolean exist = user.getRoles().add(role);
        if (!exist) {
            throw new ResourceAlreadyExistsException(Role.class.getSimpleName(), "Id", roleId.toString(), ErrorCodes.DUPLICATE_RESOURCE);
        }
        userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
