package com.adkan.adkan.phases;

import com.adkan.adkan.board.Board;
import com.adkan.adkan.projects.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@javax.persistence.Entity
@Table(name = "phases")
@Data
public class Phase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition= "varchar(60)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(nullable = false, columnDefinition= "varchar(60)")
    private String status;

    // Relationships
    @JsonIgnore
    @OneToMany(mappedBy = "phase")
    List<Board>  boards;
}
