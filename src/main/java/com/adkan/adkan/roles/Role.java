package com.adkan.adkan.roles;

import com.adkan.adkan.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "roles")
@Data
public class Role {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,  unique = true, columnDefinition= "varchar(60)")
    private String name;

    @Column(columnDefinition= "varchar(240)")
    private String description;

    // Relationships
    @JsonIgnore
    @OneToMany(mappedBy = "role")
    List<Employee> employees;
}
