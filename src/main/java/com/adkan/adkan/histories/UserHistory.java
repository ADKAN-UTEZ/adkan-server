package com.adkan.adkan.histories;

import com.adkan.adkan.board.Board;
import com.adkan.adkan.dates.AdkanDate;
import com.adkan.adkan.employee.Employee;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@javax.persistence.Entity
@Table(name = "user_history")
@Data
public class UserHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( columnDefinition= "varchar(60)")
    private String name;

    @Column(columnDefinition= "varchar(240)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "date_id", referencedColumnName = "id")
    private AdkanDate date;

    private String status;
}
