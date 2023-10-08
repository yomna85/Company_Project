package com.example.BackendTask.entity;

import com.example.BackendTask.entity.common.JPAEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "vacation_type")
public class VacationType extends JPAEntity {


    @Column(name = "english_name")
    private String englishName;

}
