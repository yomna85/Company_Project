package com.example.BackendTask.repository;

import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "Select u FROM User u WHERE u.userName= :userName")
    Optional<User> findByUsername(@Param("userName") String userName);

    @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id= :id")
    Optional<User> findRolesById(@Param("id") Integer id);

    @Query(value = "Select u FROM User u LEFT JOIN FETCH u.roles WHERE u.userName= :userName ")
    Optional<User> findRolesByUserName(@Param("userName") String userName);

    @Query(value = "Select u FROM User u LEFT JOIN FETCH u.roles",
            countQuery = "SELECT count(u) FROM  User u")
    Page<User> findAll(Pageable pageable);


}
