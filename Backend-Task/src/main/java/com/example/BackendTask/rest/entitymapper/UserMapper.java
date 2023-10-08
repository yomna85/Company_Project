package com.example.BackendTask.rest.entitymapper;

import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.entity.User;
import com.example.BackendTask.rest.dto.EmployeeDto;
import com.example.BackendTask.rest.dto.UserDto;
import com.example.BackendTask.rest.entitymapper.RoleMapper;
import com.example.BackendTask.service.RoleService;
import com.example.BackendTask.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class UserMapper {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleService roleService;

    @Mappings({
            @Mapping(source = "roles", target = "roles", ignore = true)
    })
    public abstract UserDto toDto(User user);

    public abstract List<UserDto> toDto(List<User> entityList);

    @AfterMapping
    public void toDtoAfterMapping(User entity, @MappingTarget UserDto dto) {
        if (HibernateUtils.isConvertible(entity.getRoles())) {
            dto.setRoles(roleMapper.toDto(entity.getRoles()));
        }
    }
    @InheritInverseConfiguration
    public abstract User toEntity(UserDto billDetailsDto);
    public abstract List<User> toEntity(List<UserDto> dtoList);
//    @AfterMapping
//    public void toEntityAfterMapping(UserDto dto, @MappingTarget User entity) {
//        if (dto.getRoles() != null) {
//            entity.setRoles(roleService.getById(dto.getRoles().addAll()).get());
//        }
//
//
//    }
    @InheritInverseConfiguration
    public abstract User updateEntityFromDto(UserDto billDetailsDto, @MappingTarget User billDetails);





}

