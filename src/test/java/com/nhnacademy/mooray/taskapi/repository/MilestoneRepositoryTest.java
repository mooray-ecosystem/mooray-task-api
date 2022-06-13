package com.nhnacademy.mooray.taskapi.repository;

import com.nhnacademy.mooray.taskapi.dto.milestone.MilestoneCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.entity.Milestone;
import com.nhnacademy.mooray.taskapi.entity.Project;
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
class MilestoneRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    MilestoneRepository milestoneRepository;

    private Project project;

    @BeforeEach
    void setUp() {
        project = Project.create(ProjectCreationRequest.sample());
        testEntityManager.persist(project);
    }

    @DisplayName("마일스톤 전체 조회")
    @Test
    void testFindAll() {
        Milestone milestone = Milestone.create(project, MilestoneCreationRequest.sample());
        testEntityManager.persist(milestone);

        List<Milestone> milestones = milestoneRepository.findAll();

        assertThat(milestones).contains(milestone);
    }

    @DisplayName("마일스톤 1개 저장 후 정상 조회 가능 여부 확인")
    @Test
    void testMilestoneRepository() {
        Milestone milestone = Milestone.create(project, MilestoneCreationRequest.sample());

        Milestone savedMilestone = milestoneRepository.save(milestone);
        Optional<Milestone> optionalMilestone = milestoneRepository.findById(savedMilestone.getId());

        assertThat(optionalMilestone).isPresent();
        assertThat(optionalMilestone.orElse(null)).isEqualTo(milestone);
        assertThat(optionalMilestone.orElse(null)).isEqualTo(savedMilestone);
    }

}
