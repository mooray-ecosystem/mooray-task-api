package com.nhnacademy.mooray.taskapi.service.task;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.repository.project.ProjectRepository;
import com.nhnacademy.mooray.taskapi.repository.task.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    TaskServiceImpl taskService;

    @Mock
    TaskRepository taskRepository;

    @Mock
    ProjectRepository projectRepository;

    @DisplayName("댓글 추가")
    @Test
    void testCreateTask() {
        TaskCreationRequest taskRequest = spy(TaskCreationRequest.sample());

        // Project project = Project.create(ProjectCreationRequest.sample());
        // TaskCreationRequest sample = TaskCreationRequest.sample();

        // given-when-then
        // BDDMockito.given(projectRepository.findById(1L))
        //           .willReturn(Optional.of(mock(Project.class)));
        // BDDMockito.given(taskRepository.save(any(Task.class)))
        //           .willReturn(mock(Task.class));
        BDDMockito.given(taskService.createTask(anyLong(), taskRequest))
                  .willReturn(mock(MoorayResult.class));

        // when
        MoorayResult result = taskService.createTask(anyLong(), taskRequest);

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