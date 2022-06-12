package com.nhnacademy.mooray.taskapi.service.project;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.repository.project.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

// Please remove unnecessary stubbings or use 'lenient' strictness. More info: javadoc for UnnecessaryStubbingException class.
// https://stackoverflow.com/questions/42947613/how-to-resolve-unneccessary-stubbing-exception
@ExtendWith(MockitoExtension.class)
// @MockitoSettings(strictness = Strictness.LENIENT)
@Transactional
class ProjectServiceTest {

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Mock
    private ProjectRepository projectRepository;

    @DisplayName("성공적으로 프로젝트 생성")
    @Test
    void createProject() {
        // given
        ProjectCreationRequest projectRequest = ProjectCreationRequest.sample();

        // BDDMockito.given(projectService.createProject(projectRequest))
        //           .willReturn(any(MoorayResult.class));

        // BDDMockito.given(projectService.createProject(any(ProjectCreationRequest.class)))
        //           .willReturn(MoorayResult.success(anyString(), anyMap()));

        // when
        MoorayResult result = projectService.createProject(projectRequest);

        // then
        assertThat(result).isNotNull();
        assertTrue(result.isSuccess());

        BDDMockito.verify(projectRepository, times(1))
                  .save(any());
    }

    @DisplayName("프로젝트 생성을 못함")
    @Test
    void testFailCreatingProject() {
        // given
        ProjectCreationRequest projectRequest = ProjectCreationRequest.sample();

        // BDDMockito.given(projectService.createProject(projectRequest))
        //           .willReturn(any());
        BDDMockito.given(projectRepository.existsByName(any()))
                  .willReturn(true);

        // when
        MoorayResult result = projectService.createProject(projectRequest);

        // then
        assertThat(result).isNotNull();
        assertFalse(result.isSuccess());

        // BDDMockito.verify(projectRepository, times(1)).count();
    }

    @Test
    void updateProject() {

    }

}