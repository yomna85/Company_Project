package com.example.BackendTask.rest.entitymapper;

import com.example.BackendTask.entity.Role;
import com.example.BackendTask.rest.dto.RoleDto;
import com.example.BackendTask.rest.entitymapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Set;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RoleMapper extends JPAEntityMapper<Role, RoleDto> {
    Set<RoleDto> toDto(Set<Role> roles);
}