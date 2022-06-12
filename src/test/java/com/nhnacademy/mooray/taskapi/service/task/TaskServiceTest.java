package com.nhnacademy.mooray.taskapi.service.task;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.task.TaskUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.entity.Task;
import com.nhnacademy.mooray.taskapi.repository.ProjectRepository;
import com.nhnacademy.mooray.taskapi.repository.TaskRepository;
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
class TaskServiceTest {

    @InjectMocks
    TaskServiceImpl taskService;

    @Mock
    TaskRepository taskRepository;

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

    @DisplayName("성공적으로 업무 추가")
    @Test
    void testCreateTask() {
        // 2. Task repository mocking
        TaskCreationRequest taskRequest = spy(TaskCreationRequest.sample());
        Task task = Task.create(project, taskRequest);
        BDDMockito.given(taskRepository.save(any(Task.class)))
                  .willReturn(task);

        // 3. when
        MoorayResult<Task> result = taskService.createTask(1L, taskRequest);

        // then
        assertThat(result).isNotNull();
    }

    @DisplayName("특정 프로젝트에 생성되어 있는 업무 목록 조회")
    @Test
    void testRetrieveTasks() {
        TaskCreationRequest taskRequest1 = spy(TaskCreationRequest.sample());
        Task task1 = Task.create(project, taskRequest1);
        BDDMockito.lenient()
                  .when(taskRepository.save(any(Task.class)))
                  .thenReturn(task1);

        TaskCreationRequest taskRequest2 = spy(TaskCreationRequest.sample());
        Task task2 = Task.create(project, taskRequest2);
        BDDMockito.lenient()
                  .when(taskRepository.save(any(Task.class)))
                  .thenReturn(task2);

        BDDMockito.lenient()
                  .when(taskRepository.findAll())
                  .thenReturn(List.of(task1, task2));

        MoorayResult<List<Task>> result = taskService.retrieveTasks(1L);

        assertThat(result).isNotNull();
        assertThat(result.getPayload()).containsEntry("data", List.of(task1, task2));
    }

    @DisplayName("특정 프로젝트에 생성되어 있는 업무 목록 중 특정 업무 1개 조회")
    @Test
    void testRetrieveTask() {
        TaskCreationRequest taskRequest = spy(TaskCreationRequest.sample());
        Task task = Task.create(project, taskRequest);

        BDDMockito.lenient()
                  .when(taskRepository.findById(1L))
                  .thenReturn(Optional.ofNullable(task));
        BDDMockito.lenient()
                  .when(taskRepository.save(any(Task.class)))
                  .thenReturn(task);

        MoorayResult<Task> result = taskService.retrieveTask(1L);

        assertThat(result).isNotNull();
        assertThat(result.getPayload()).containsEntry("data", task);
    }

    @DisplayName("특정 프로젝트에 생성되어 있는 업무 목록 중 1개 업무 수정")
    @Test
    void testUpdateTask() {
        TaskCreationRequest creationRequest = spy(TaskCreationRequest.sample());
        Task originalTask = Task.create(project, creationRequest);
        BDDMockito.lenient()
                  .when(taskRepository.save(any(Task.class)))
                  .thenReturn(originalTask);

        BDDMockito.lenient()
                  .when(taskRepository.findById(1L))
                  .thenReturn(Optional.of(originalTask));

        TaskUpdateRequest updateRequest = spy(TaskUpdateRequest.sample());
        Task updatedTask = Task.create(originalTask, updateRequest);

        BDDMockito.lenient()
                  .when(taskRepository.findById(1L))
                  .thenReturn(Optional.of(updatedTask));

        // when
        MoorayResult<Task> result = taskService.updateTask(1L, updateRequest);

        assertThat(result).isNotNull();
    }

    @DisplayName("특정 프로젝트에 생성되어 있는 업무 목록 중 1개 업무 삭제")
    @Test
    void testDeleteTask() {
        TaskCreationRequest taskRequest = spy(TaskCreationRequest.sample());
        Task task = Task.create(project, taskRequest);

        BDDMockito.lenient()
                  .when(taskRepository.findById(1L))
                  .thenReturn(Optional.ofNullable(task));
        BDDMockito.lenient()
                  .when(taskRepository.save(any(Task.class)))
                  .thenReturn(task);

        MoorayResult<Boolean> result = taskService.deleteTask(1L);

        assertThat(result).isNotNull();
        assertThat(result.getPayload()).containsEntry("data", true);
    }

}