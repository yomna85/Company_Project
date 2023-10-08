package com.example.BackendTask.entity;

import com.example.BackendTask.entity.common.LookupEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class Role extends LookupEntity {

    @Column(name = "code")
    private String code;
}

