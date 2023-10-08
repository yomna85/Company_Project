package com.example.BackendTask.rest.handler;

import com.example.BackendTask.entity.Role;
import com.example.BackendTask.rest.dto.RoleDto;
import com.example.BackendTask.rest.dto.common.PaginatedResultDto;
import com.example.BackendTask.rest.entitymapper.RoleMapper;
import com.example.BackendTask.rest.entitymapper.common.PaginationMapper;
import com.example.BackendTask.rest.exception.ResourceNotFoundException;
import com.example.BackendTask.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
@AllArgsConstructor
public class RoleHandler {
    private RoleService roleService;
    private RoleMapper roleMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getById(Integer id) {
        Role role = roleService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), id));
        RoleDto roleDto = roleMapper.toDto(role);
        return ResponseEntity.ok(roleDto);
    }

    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<Role> rolePage = roleService.getAll(page, size);
        List<RoleDto> roleDtoList = roleMapper.toDto(rolePage.getContent());
        PaginatedResultDto<RoleDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(roleDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(rolePage));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> save(RoleDto dto)
    {
        Role role= roleMapper.toEntity(dto);
        role= roleService.save(role);
        dto=roleMapper.toDto(role);
        return ResponseEntity.created(URI.create("/role/" + role.getId())).body(dto);
    }


}
