package com.nhnacademy.mooray.taskapi.repository;

import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    TaskRepository taskRepository;

    private Project project;

    @BeforeEach
    void setUp() {
        project = Project.create(ProjectCreationRequest.sample());
        testEntityManager.persist(project);
    }

    @DisplayName("업무 전체 조회")
    @Test
    public void testFindAll() {
        // DATA
        Task task = Task.create(project, TaskCreationRequest.sample());
        testEntityManager.persist(task);

        // TEST
        List<Task> tasks = taskRepository.findAll();

        // Assertion
        assertThat(tasks).contains(task);
    }

    @DisplayName("업무 1건 저장 후 정상 조회 가능 여부 확인")
    @Test
    void testTaskRepository() {
        // DATA
        Task task = Task.create(project, TaskCreationRequest.sample());

        // TEST
        Task savedTask = taskRepository.save(task);
        Optional<Task> optionalTask = taskRepository.findById(savedTask.getId());

        // Assertions
        assertThat(optionalTask).isPresent();
        assertThat(optionalTask.orElse(null)).isEqualTo(task);
        assertThat(optionalTask.orElse(null)).isEqualTo(savedTask);
    }

}
