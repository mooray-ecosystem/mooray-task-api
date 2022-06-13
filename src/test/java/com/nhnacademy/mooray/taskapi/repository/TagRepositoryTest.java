package com.nhnacademy.mooray.taskapi.repository;

import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.tag.TagCreationRequest;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.entity.Tag;
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
class TagRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    TagRepository tagRepository;

    private Project project;

    @BeforeEach
    void setUp() {
        project = Project.create(ProjectCreationRequest.sample());
        testEntityManager.persist(project);
    }

    @DisplayName("태그 전체 조회")
    @Test
    void testFindAll() {
        Tag tag = Tag.create(project, TagCreationRequest.sample());
        testEntityManager.persist(tag);

        List<Tag> tags = tagRepository.findAll();

        assertThat(tags).contains(tag);
    }

    @DisplayName("태그 1개 저장 후 정상 조회 가능 여부 확인")
    @Test
    void testTagRepository() {
        Tag tag = Tag.create(project, TagCreationRequest.sample());

        Tag savedTag = tagRepository.save(tag);
        Optional<Tag> optionalTag = tagRepository.findById(savedTag.getId());

        assertThat(optionalTag).isPresent();
        assertThat(optionalTag.orElse(null)).isEqualTo(tag);
        assertThat(optionalTag.orElse(null)).isEqualTo(savedTag);
    }

}
