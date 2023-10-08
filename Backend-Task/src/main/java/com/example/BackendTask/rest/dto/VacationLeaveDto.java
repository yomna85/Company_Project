package com.example.BackendTask.rest.dto;

import com.example.BackendTask.rest.dto.common.RestDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class VacationLeaveDto extends RestDto {

    private Date startDate;
    private Date endDate;
    private LocalDateTime requestDate;
    private EmployeeDto employee;
    private VacationTypeDto vacationType;

}
