package com.example.BackendTask.rest.dto;

import com.example.BackendTask.rest.dto.common.RestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Set;

@Data
public class UserDto extends RestDto {
    private String userName;

    private String password;

    private String name;

    private String arabic_Name;

    private String status;

    private Set<RoleDto> roles;

    @JsonIgnore
    @JsonProperty(value = "user_password")
    public String getPassword() {
        return password;
    }
}
