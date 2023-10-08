package com.example.BackendTask.entity.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@MappedSuperclass
@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = "id", callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class JPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public JPAEntity() {
    }

    public JPAEntity(Integer id) {
        this.id=id;
    }
}
