package com.example.BackendTask.rest.entitymapper;

import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.rest.dto.EmployeeDto;
import com.example.BackendTask.service.DepartmentService;
import com.example.BackendTask.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)

public  abstract class EmployeeMapper {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DepartmentService departmentService;


    @Mappings({
            @Mapping(source = "department", target = "department", ignore = true),


    })

    public abstract EmployeeDto toDto(Employee employee);

    public abstract List<EmployeeDto> toDto(List<Employee> employees);

    @AfterMapping
    public void toDtoAfterMapping(Employee entity, @MappingTarget EmployeeDto dto) {
        if (HibernateUtils.isConvertible(entity.getDepartment())) {
            dto.setDepartment(departmentMapper.toDto(entity.getDepartment()));
        }


    }
    @InheritInverseConfiguration
    public abstract Employee toEntity(EmployeeDto billDetailsDto);

    public abstract List<Employee> toEntity(List<EmployeeDto> list);
    @AfterMapping
    public void toEntityAfterMapping(EmployeeDto dto, @MappingTarget Employee entity) {
        if (dto.getDepartment() != null) {
            entity.setDepartment(departmentService.getById(dto.getDepartment().getId()).get());
        }


    }
    @InheritInverseConfiguration
    public abstract Employee updateEntityFromDto(EmployeeDto billDetailsDto, @MappingTarget Employee billDetails);




}