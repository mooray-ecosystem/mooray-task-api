package com.nhnacademy.mooray.taskapi.service.milestone;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.milestone.MilestoneCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.milestone.MilestoneUpdateRequest;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.entity.Milestone;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.repository.MilestoneRepository;
import com.nhnacademy.mooray.taskapi.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class MilestoneServiceTest {

    @InjectMocks
    MilestoneServiceImpl milestoneService;

    @Mock
    MilestoneRepository milestoneRepository;

    @Mock
    ProjectRepository projectRepository;

    private Project project;

    @BeforeEach
    void setUp() {
        // 1. Task 는 특정 프로젝트에 귀속되어 있으므로 프로젝트를 1개 조회 (mocking)
        project = spy(Project.create(ProjectCreationRequest.sample()));
        BDDMockito.lenient()
                  .when(projectRepository.findById(1L))
                  .thenReturn(Optional.of(project));
    }

    @DisplayName("성공적으로 마일스톤 등록")
    @Test
    void testCreateMilestone() {
        MilestoneCreationRequest milestoneRequest = spy(MilestoneCreationRequest.sample());
        Milestone milestone = Milestone.create(project, milestoneRequest);
        BDDMockito.given(milestoneRepository.save(any(Milestone.class)))
                  .willReturn(milestone);

        MoorayResult<Milestone> result = milestoneService.createMilestone(1L, milestoneRequest);

        assertThat(result).isNotNull();
    }

    @DisplayName("특정 프로젝트에 생성되어 있는 마일스톤 목록 조회")
    @Test
    void testRetrieveMilestones() {
        MilestoneCreationRequest milestoneRequest1 = spy(MilestoneCreationRequest.sample());
        Milestone milestone1 = Milestone.create(project, milestoneRequest1);
        BDDMockito.lenient()
                  .when(milestoneRepository.save(any(Milestone.class)))
                  .thenReturn(milestone1);

        MilestoneCreationRequest milestoneRequest2 = spy(MilestoneCreationRequest.sample());
        Milestone milestone2 = Milestone.create(project, milestoneRequest2);
        BDDMockito.lenient()
                  .when(milestoneRepository.save(any(Milestone.class)))
                  .thenReturn(milestone2);

        BDDMockito.lenient()
                  .when(milestoneRepository.findAll())
                  .thenReturn(List.of(milestone1, milestone2));

        MoorayResult<List<Milestone>> result = milestoneService.retrieveMilestones(1L);

        assertThat(result).isNotNull();
        assertThat(result.getPayload()).containsEntry("data", List.of(milestone1, milestone2));
    }

    @DisplayName("특정 프로젝트에 생성되어 있는 마일스톤 목록 중 특정 마일스톤 1개 조회")
    @Test
    void testRetrieveMilestone() {
        MilestoneCreationRequest milestoneRequest = spy(MilestoneCreationRequest.sample());
        Milestone milestone = Milestone.create(project, milestoneRequest);

        BDDMockito.lenient()
                  .when(milestoneRepository.findById(1L))
                  .thenReturn(Optional.ofNullable(milestone));
        BDDMockito.lenient()
                  .when(milestoneRepository.save(any(Milestone.class)))
                  .thenReturn(milestone);

        MoorayResult<Milestone> result = milestoneService.retrieveMilestone(1L, 1L);

        assertThat(result).isNotNull();
        assertThat(result.getPayload()).containsEntry("data", milestone);
    }

    @DisplayName("특정 프로젝트에 생성되어 있는 마일스톤 목록 중 특정 마일스톤 1개 수정")
    @Test
    void testUpdateMilestone() {
        MilestoneCreationRequest creationRequest = spy(MilestoneCreationRequest.sample());
        Milestone originalMilestone = Milestone.create(project, creationRequest);
        BDDMockito.lenient()
                  .when(milestoneRepository.save(any(Milestone.class)))
                  .thenReturn(originalMilestone);

        BDDMockito.lenient()
                  .when(milestoneRepository.findById(1L))
                  .thenReturn(Optional.of(originalMilestone));

        MilestoneUpdateRequest updateRequest = spy(MilestoneUpdateRequest.sample());
        Milestone updatedMilestone = Milestone.create(originalMilestone, updateRequest);

        BDDMockito.lenient()
                  .when(milestoneRepository.findById(1L))
                  .thenReturn(Optional.of(updatedMilestone));

        // when
        MoorayResult<Milestone> result = milestoneService.updateMilestone(1L, 1L, updateRequest);

        assertThat(result).isNotNull();
    }

    @DisplayName("특정 프로젝트에 생성되어 있는 마일스톤 목록 중 특정 마일스톤 1개 삭제")
    @Test
    void testDeleteMilestone() {
        MilestoneCreationRequest milestoneRequest = spy(MilestoneCreationRequest.sample());
        Milestone milestone = Milestone.create(project, milestoneRequest);

        BDDMockito.lenient()
                  .when(milestoneRepository.findById(1L))
                  .thenReturn(Optional.ofNullable(milestone));
        BDDMockito.lenient()
                  .when(milestoneRepository.save(any(Milestone.class)))
                  .thenReturn(milestone);

        MoorayResult<Boolean> result = milestoneService.deleteMilestone(1L, 1L);

        assertThat(result).isNotNull();
        assertThat(result.getPayload()).containsEntry("data", true);
    }

}