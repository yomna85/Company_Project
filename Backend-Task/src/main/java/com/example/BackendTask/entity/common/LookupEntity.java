package com.example.BackendTask.entity.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@EqualsAndHashCode(of = {"arabicName", "englishName"}, callSuper = true)
@ToString(of = {"arabicName", "englishName"}, callSuper = true)
public class LookupEntity  extends JPAEntity {

    @Column(name = "arabic_name")
    private String arabicName;

    @Column(name = "english_name")
    private String englishName;


}
