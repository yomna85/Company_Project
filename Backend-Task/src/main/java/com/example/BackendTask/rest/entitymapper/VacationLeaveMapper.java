package com.example.BackendTask.rest.entitymapper;

import com.example.BackendTask.entity.VacationLeave;
import com.example.BackendTask.rest.dto.VacationLeaveDto;
import com.example.BackendTask.service.EmployeeService;
import com.example.BackendTask.service.VacationTypeService;
import com.example.BackendTask.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class VacationLeaveMapper {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private VacationTypeMapper vacationTypeMapper;
    @Autowired
    private VacationTypeService vacationTypeService;


    @Mappings(value = {
            @Mapping(source = "employee", target = "employee", ignore = true),
            @Mapping(source = "vacationType", target = "vacationType", ignore = true)
    })

    public abstract VacationLeaveDto toDto(VacationLeave vacationLeave);

    public abstract List<VacationLeaveDto> toDto(List<VacationLeave> vacationLeaveList);

    @AfterMapping
    public void toDtoAfterMapping(VacationLeave entity, @MappingTarget VacationLeaveDto dto) {
        if (HibernateUtils.isConvertible(entity.getEmployee())) {
            dto.setEmployee(employeeMapper.toDto(entity.getEmployee()));
        }
        if (HibernateUtils.isConvertible(entity.getVacationType())) {
            dto.setVacationType(vacationTypeMapper.toDto(entity.getVacationType()));
        }

    }
    @InheritInverseConfiguration
    public abstract VacationLeave toEntity(VacationLeaveDto vacationLeaveDto);

    public abstract List<VacationLeave> toEntity(List<VacationLeaveDto> vacationLeaveDtoList);


    @AfterMapping
    public void toEntityAfterMapping(VacationLeaveDto dto, @MappingTarget VacationLeave entity) {
        if (dto.getEmployee() != null) {
            entity.setEmployee(employeeService.getById(dto.getEmployee().getId()).get());
        }
        if (dto.getVacationType() != null) {
            entity.setVacationType(vacationTypeService.getById(dto.getVacationType().getId()).get());
        }


    }
    @InheritInverseConfiguration
    public abstract VacationLeave updateEntityFromDto(VacationLeaveDto dto, @MappingTarget VacationLeave entity);



}

