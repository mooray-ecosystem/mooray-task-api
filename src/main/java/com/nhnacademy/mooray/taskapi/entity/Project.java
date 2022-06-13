package com.nhnacademy.mooray.taskapi.entity;

import com.nhnacademy.mooray.taskapi.domain.ProjectStatus;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

import static java.time.LocalDate.now;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "Projects")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
@Getter // DataJpaTest 에서 테스트 용도
public class Project {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "project_no")
    private Long projectId;

    @Column(name = "admin_no")
    private Long adminId;

    private String name;

    @Enumerated(value = STRING)
    private ProjectStatus status;

    @Column(name = "created_at")
    private LocalDate createdDate;

    public static Project create(ProjectCreationRequest projectRequest) {
        return Project.builder()
                      .name(projectRequest.getName())
                      .status(ProjectStatus.valueOf(projectRequest.getStatus()))
                      .adminId(projectRequest.getAdminId())
                      .createdDate(now())
                      .build();
    }

    public static Project create(Project originalProject, ProjectUpdateRequest projectRequest) {
        return Project.builder()
                      .adminId(Optional.ofNullable(projectRequest.getAdminId()).orElse(originalProject.adminId))
                      .name(Optional.ofNullable(projectRequest.getName()).orElse(originalProject.name))
                      .status(Optional.of(ProjectStatus.valueOf(projectRequest.getStatus()))
                                      .orElse(originalProject.status))
                      .build();
    }

}
