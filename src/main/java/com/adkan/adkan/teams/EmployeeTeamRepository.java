package com.adkan.adkan.teams;

import com.adkan.adkan.employee.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeTeamRepository extends CrudRepository<EmployeeTeam, Integer> {
    List<Employee> getByTeamId(Integer id);
    List<Employee> getByTeamName(String name);
}
