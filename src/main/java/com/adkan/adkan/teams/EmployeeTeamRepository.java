package com.adkan.adkan.teams;

import com.adkan.adkan.employee.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeTeamRepository extends CrudRepository<EmployeeTeam, Integer> {
}
