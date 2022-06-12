package com.nhnacademy.mooray.taskapi.repository.project;

import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.repository.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

// MEMO: DataJpaTest 내부에 @Transactional 이 있음
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    ProjectRepository projectRepository;

    @Order(1)
    @DisplayName("프로젝트 전체 조회")
    @Test
    void testFindAll() {
        // DATA
        Project project = Project.create(ProjectCreationRequest.sample());
        testEntityManager.persist(project);

        // TEST
        List<Project> projects = projectRepository.findAll();

        // Assertion
        assertThat(projects).contains(project);
    }

    @Order(2)
    @DisplayName("특정 프로젝트 1건 저장 후 정상 조회 가능 여부 확인")
    @Test
    void testProjectRepository() {
        // DATA
        Project project = Project.create(ProjectCreationRequest.sample());
        Project savedProject = projectRepository.save(project);
        Optional<Project> optionalProject = projectRepository.findById(savedProject.getProjectId());

        // Assertions
        assertThat(optionalProject).isPresent();
        assertThat(optionalProject.orElse(null)).isEqualTo(project);
        assertThat(optionalProject.orElse(null)).isEqualTo(savedProject);
    }

    @Order(3)
    @DisplayName("프로젝트의 이름을 통한 중복 여부 확인")
    @Test
    void testExistsByName() {
        Project project = Project.create(ProjectCreationRequest.sample());
        testEntityManager.persist(project);

        boolean actual = projectRepository.existsByName(project.getName());

        assertTrue(actual);
    }

}