package com.nhnacademy.mooray.taskapi.service.comment;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.entity.Comment;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.entity.Task;
import com.nhnacademy.mooray.taskapi.repository.CommentRepository;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    CommentServiceImpl commentService;

    @Mock
    CommentRepository commentRepository;

    @Mock
    TaskRepository taskRepository;

    @Mock
    ProjectRepository projectRepository;

    private Project project;
    private Task task;

    @BeforeEach
    void setUp() {
        // 1. 업무(Task)는 특정 프로젝트(Project)에 귀속되어 있으므로 프로젝트 mocking
        project = spy(Project.create(ProjectCreationRequest.sample()));
        BDDMockito.lenient()
                  .when(projectRepository.findById(anyLong()))
                  .thenReturn(Optional.of(project));

        // 2. 댓글 또한 특정 업무(Task)에 귀속되어 있으므로 업무 mocking
        task = spy(Task.create(project, spy(TaskCreationRequest.sample())));
        BDDMockito.lenient()
                  .when(taskRepository.findById(anyLong()))
                  .thenReturn(Optional.of(task));

        // Contributor: eastheat10
        BDDMockito.given(task.getId()).willReturn(1L);
    }

    @DisplayName("성공적으로 특정 업무에 댓글 작성")
    @Test
    void testCreateComment() {
        // ready
        CommentCreationRequest commentRequest = spy(CommentCreationRequest.sample());
        Comment comment = Comment.create(task, commentRequest);

        // given
        BDDMockito.given(commentRepository.save(any(Comment.class)))
                  .willReturn(comment);

        // when
        MoorayResult<Comment> result
                = commentService.createComment(project.getProjectId(), task.getId(), commentRequest);

        // then
        assertThat(result).isNotNull();
    }

    @DisplayName("성공적으로 특정 업무에 대해 댓글을 조회합니다.")
    @Test
    void testRetrieveComments() {
    }

    @DisplayName("")
    @Test
    void testRetrieveComment() {
    }

    @DisplayName("")
    @Test
    void testUpdateComment() {
    }

    @DisplayName("")
    @Test
    void testDeleteComment() {
    }

}
