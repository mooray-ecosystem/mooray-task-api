package com.nhnacademy.mooray.taskapi.service.task;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.entity.Task;
import com.nhnacademy.mooray.taskapi.repository.project.ProjectRepository;
import com.nhnacademy.mooray.taskapi.repository.task.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    TaskServiceImpl taskService;

    @Mock
    TaskRepository taskRepository;

    @Mock
    ProjectRepository projectRepository;

    @DisplayName("")
    @Test
    void testCreateTask() {

        // Project project = Project.create(ProjectCreationRequest.sample());
        // TaskCreationRequest sample = TaskCreationRequest.sample();

        // doReturn-when-method
        // BDDMockito.doReturn(mock(Project.class))
        //           .when(projectRepository)
        //           .findById(1L);
        // BDDMockito.doReturn(Optional.of(mock(Task.class)))
        //           .when(taskRepository);
                  // .save(Task.create(project, sample));

        // when
        MoorayResult result = taskService.createTask(1L, TaskCreationRequest.sample());

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