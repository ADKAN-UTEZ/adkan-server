package com.adkan.adkan.employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Optional<Employee> findByEmailAndStatus(String email, Boolean status);
}
