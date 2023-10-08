package com.example.BackendTask.rest.dto;

import com.example.BackendTask.rest.dto.common.RestDto;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDto  extends RestDto {


    private String employeeCode;

    private String name;

    private String arabicName;

    private Integer age;

    private String gender;

    private String maritalStatus;

    private String phoneNumber;

    private String address;

    private Date JoiningDate;

    private Date dateOfBirth;

    private String status;

    private DepartmentDto department;

}
