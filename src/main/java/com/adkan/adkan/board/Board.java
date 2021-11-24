package com.adkan.adkan.board;

import com.adkan.adkan.phases.Phase;
import com.adkan.adkan.user_histories.UserHistory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@javax.persistence.Entity
@Table(name = "board")
@Data
public class Board implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition= "varchar(60)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "phase_id")
    private Phase phase;

    @Column(columnDefinition= "varchar(20)")
    private String color;

    // Relationships
    @JsonIgnore
    @OneToMany(mappedBy = "board")
    List<UserHistory> userHistories;
}
