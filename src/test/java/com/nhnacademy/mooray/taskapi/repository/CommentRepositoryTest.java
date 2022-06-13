package com.nhnacademy.mooray.taskapi.repository;

import com.nhnacademy.mooray.taskapi.dto.comment.CommentCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.entity.Comment;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    CommentRepository commentRepository;

    private Project project;
    private Task task;

    @BeforeEach
    void setUp() {
        project = Project.create(ProjectCreationRequest.sample());
        testEntityManager.persist(project);

        task = Task.create(project, TaskCreationRequest.sample());
        testEntityManager.persist(task);
    }

    @DisplayName("댓글 전체 조회")
    @Test
    void testFindAll() {
        Comment comment = Comment.create(project.getProjectId(), task, CommentCreationRequest.sample());
        testEntityManager.persist(comment);

        List<Comment> comments = commentRepository.findAll();

        assertThat(comments).contains(comment);
    }

    @DisplayName("업무 1건 저장 후 정상 조회 가능 여부 확인")
    @Test
    void testCommentRepository() {
        Comment comment = Comment.create(project.getProjectId(), task, CommentCreationRequest.sample());

        Comment savedComment = commentRepository.save(comment);
        Optional<Comment> optionalComment = commentRepository.findById(savedComment.getId());

        assertThat(optionalComment).isPresent();
        assertThat(optionalComment.orElse(null)).isEqualTo(comment);
        assertThat(optionalComment.orElse(null)).isEqualTo(savedComment);
    }

}