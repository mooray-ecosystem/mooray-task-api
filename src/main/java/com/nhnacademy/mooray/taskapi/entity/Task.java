package com.nhnacademy.mooray.taskapi.entity;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "task_no")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "milestone_no")
    private Milestone milestone;

    private String title;

    private String content;

    @Column(name = "created_at")
    private LocalDate createdDate;

    @Column(name = "updated_at")
    private LocalDate updatedDate;

    @Column(name = "deleted_at")
    private LocalDate deletedDate;

}
