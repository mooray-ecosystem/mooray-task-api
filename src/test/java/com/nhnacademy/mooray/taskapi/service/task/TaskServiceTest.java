package com.nhnacademy.mooray.taskapi.service.task;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.entity.Task;
import com.nhnacademy.mooray.taskapi.repository.ProjectRepository;
import com.nhnacademy.mooray.taskapi.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    TaskServiceImpl taskService;

    @Mock
    TaskRepository taskRepository;

    @Mock
    ProjectRepository projectRepository;

    @DisplayName("성공적으로 업무 추가")
    @Test
    void testCreateTask() {
        // 1. Task 는 특정 프로젝트에 귀속되어 있으므로 프로젝트를 1개 조회 (mocking)

        // 2. Task repository mocking

        // 3.
        Project project = spy(Project.create(ProjectCreationRequest.sample()));
        BDDMockito.given(projectRepository.findById(1L))
                  .willReturn(Optional.of(project));

        TaskCreationRequest taskRequest = spy(TaskCreationRequest.sample());
        Task task = Task.create(project, taskRequest);
        BDDMockito.given(taskRepository.save(any(Task.class)))
                  .willReturn(task);

        // when
        MoorayResult result = taskService.createTask(1L, taskRequest);

        // doReturn-when-method
        // BDDMockito.doReturn(mock(Project.class))
        //           .when(projectRepository)
        //           .findById(1L);
        // BDDMockito.doReturn(Optional.of(mock(Task.class)))
        //           .when(taskRepository);
        //           .save(Task.create(project, sample));

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void testRetrieveTasks() {
    }

    @Test
    void retrieveTask() {
    }

    @Test
    void updateTask() {
    }

    @Test
    void deleteTask() {
    }
}