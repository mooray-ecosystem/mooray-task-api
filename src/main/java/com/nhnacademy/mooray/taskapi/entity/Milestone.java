package com.nhnacademy.mooray.taskapi.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Milestones")
public class Milestone {

    @Id
    @Column(name = "milestone_no")
    private Long id;

    @OneToOne
    @JoinColumn(name = "project_no")
    private Project project;

    private String title;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

}
