package com.nhnacademy.mooray.taskapi.entity;

import com.nhnacademy.mooray.taskapi.dto.milestone.MilestoneCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.milestone.MilestoneUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "Milestones")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
@Getter
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

    public static Milestone create(Project project, MilestoneCreationRequest milestoneRequest) {
        return Milestone.builder()
                        .project(project)
                        .title(milestoneRequest.getTitle())
                        .startDate(milestoneRequest.getStartDate())
                        .endDate(milestoneRequest.getEndDate())
                        .build();
    }

    public static Milestone create(Milestone originalMilestone, MilestoneUpdateRequest milestoneRequest) {
        return Milestone.builder()
                        .project(originalMilestone.project)
                        .title(Optional.ofNullable(milestoneRequest.getTitle()).orElse(originalMilestone.title))
                        .startDate(Optional.ofNullable(milestoneRequest.getStartDate())
                                           .orElse(originalMilestone.startDate))
                        .endDate(Optional.ofNullable(milestoneRequest.getEndDate())
                                         .orElse(originalMilestone.endDate))
                        .build();
    }

}
