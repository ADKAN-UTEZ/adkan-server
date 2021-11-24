package com.adkan.adkan.projects;

import com.adkan.adkan.dates.AdkanDate;
import com.adkan.adkan.phases.Phase;
import com.adkan.adkan.teams.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@javax.persistence.Entity
@Table(name = "projects")
@Data
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,  unique = true, columnDefinition= "varchar(20)")
    private String shortName;

    @Column(nullable = false, columnDefinition= "varchar(60)")
    private String name;

    @Column(columnDefinition= "varchar(450)")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "date_id", referencedColumnName = "id")
    private AdkanDate date;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private String status;


    // Relationships
    @JsonIgnore
    @OneToMany(mappedBy = "project")
    List<Phase> phases;

}
