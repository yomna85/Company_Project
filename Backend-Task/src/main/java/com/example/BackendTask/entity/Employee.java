package com.example.BackendTask.entity;

import com.example.BackendTask.entity.common.JPAEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "employee")
public class Employee  extends JPAEntity {

    @Column(name = "employee_code")
    private String employeeCode;

    @Column(name = "name")
    private String name;

    @Column(name="arabic_name")
    private String arabic_Name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "joining_date")
    private Date JoiningDate;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;





    }

