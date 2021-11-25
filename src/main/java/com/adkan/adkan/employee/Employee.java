package com.adkan.adkan.employee;

import com.adkan.adkan.roles.Role;
import com.adkan.adkan.teams.EmployeeTeam;
import com.adkan.adkan.histories.UserHistory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@javax.persistence.Entity
@Table(name = "employees")
@Data
public class Employee implements Serializable {
    /** Entity Class
     * In this kind of class the mapping of the database is reflected
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,  unique = true, columnDefinition= "varchar(60)")
    private String enrollment;

    @Column(nullable = false,  unique = true, columnDefinition= "varchar(60)")
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition= "varchar(60)")
    private String name;

    @Column(columnDefinition= "varchar(60)")
    private String lastName;

    @Column(nullable = false, columnDefinition= "varchar(60)")
    private String secondName;

    @Column(name = "is_available", nullable = false, columnDefinition= "boolean default true")
    private Boolean isAvailable;

    @Column(name = "status", nullable = false, columnDefinition= "boolean default true")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Relationships
    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    List<EmployeeTeam> employeeTeamList;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    List<UserHistory> histories;

}
