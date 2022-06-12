package com.nhnacademy.mooray.taskapi.entity;

import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.task.TaskUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

import static java.time.LocalDate.now;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "Tasks")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
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

    public static Task create(Project project, TaskCreationRequest taskRequest) {
        return Task.builder()
                   .project(project)
                   .title(taskRequest.getTitle())
                   .content(taskRequest.getContent())
                   .createdDate(now())
                   .updatedDate(now())
                   .build();
    }

    public static Task create(Task originalTask, TaskUpdateRequest taskRequest) {
        return Task.builder()
                   .title(Optional.ofNullable(taskRequest.getTitle()).orElse(originalTask.title))
                   .content(Optional.ofNullable(taskRequest.getContent()).orElse(originalTask.content))
                   .updatedDate(now())
                   .build();
    }

}
