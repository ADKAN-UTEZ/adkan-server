package com.adkan.adkan.teams;

import com.adkan.adkan.projects.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@javax.persistence.Entity
@Table(name = "teams")
@Data
public class Team implements Serializable {
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
    @OneToMany(mappedBy = "team")
    List<EmployeeTeam> employeeTeamList;

    @JsonIgnore
    @OneToMany(mappedBy = "team")
    List<Project> projects;
}
