package com.example.BackendTask.rest.dto;

import com.example.BackendTask.rest.dto.common.RestDto;
import lombok.Data;

@Data
public class DepartmentDto extends RestDto {

    private String name;

    private String arabicName;
}
