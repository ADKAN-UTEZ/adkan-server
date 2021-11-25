package com.adkan.adkan.dates;

import com.adkan.adkan.histories.UserHistory;
import com.adkan.adkan.projects.Project;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@javax.persistence.Entity
@Table(name = "dates")
@Data
public class AdkanDate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "planned_start", nullable = false, updatable = false)
    private LocalDateTime plannedStart;

    @Column(name = "real_start")
    private LocalDateTime realStart;

    @Column(name = "planned_end", updatable = false)
    private LocalDateTime plannedEnd;

    @Column(name = "real_end")
    private LocalDateTime realEnd;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "date")
    private Project project;

    @OneToOne(mappedBy = "date")
    private UserHistory userHistory;
}
