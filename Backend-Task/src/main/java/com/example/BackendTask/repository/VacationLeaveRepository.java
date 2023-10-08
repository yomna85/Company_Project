package com.example.BackendTask.repository;

import com.example.BackendTask.entity.VacationLeave;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VacationLeaveRepository  extends JpaRepository<VacationLeave, Integer> {

    @Query(value = " SELECT v from VacationLeave v  JOIN FETCH v.employee  as A  JOIN FETCH v.vacationType as B WHERE v.employee.id = :employeeId ",
            countQuery = "SELECT count(v) FROM VacationLeave v WHERE v.employee.id = :employeeId")
    Page<VacationLeave> findAllByEmployee(@Param(value = "employeeId") Integer employeeId, Pageable pageable);

    @Query(value = " SELECT v from VacationLeave v  JOIN FETCH v.employee   JOIN FETCH v.vacationType  WHERE v.id = :id ",
            countQuery = "SELECT count(v) FROM VacationLeave v WHERE v.id = :id")
    Optional<VacationLeave> findById(@Param(value = "id") Integer id);


}
