package com.adkan.adkan.teams;

import com.adkan.adkan.employee.Employee;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@javax.persistence.Entity
@Table(name = "employees_teams")
@Data
public class EmployeeTeam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
