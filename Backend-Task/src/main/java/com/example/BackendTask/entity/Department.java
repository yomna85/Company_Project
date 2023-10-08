package com.example.BackendTask.entity;

import com.example.BackendTask.entity.common.JPAEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "department")
public class Department  extends JPAEntity {
    @Column(name = "name")
    private String name;
    @Column(name="arabic_name")
    private String arabic_Name;


}
