package com.example.BackendTask.rest.dto;

import com.example.BackendTask.rest.dto.common.RestDto;
import lombok.Data;

@Data
public class RoleDto extends RestDto {
    private String arabicName;

    private String englishName;

    private String code;

}
