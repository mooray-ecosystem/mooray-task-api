package com.nhnacademy.mooray.taskapi.service.comment;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentUpdateRequest;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

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
        project = spy(Project.create(ProjectCreationRequest.sample()));
        BDDMockito.lenient()
                  .when(projectRepository.findById(1L))
                  .thenReturn(Optional.of(project));

        task = spy(Task.create(project, TaskCreationRequest.sample()));
        BDDMockito.lenient()
                  .when(taskRepository.findById(1L))
                  .thenReturn(Optional.of(task));
    }

    @DisplayName("성공적으로 댓글 추가")
    @Test
    void testCreateComment() {
        CommentCreationRequest commentRequest = CommentCreationRequest.sample();
        Comment comment = Comment.create(1L, task, commentRequest);
        BDDMockito.given(commentRepository.save(any(Comment.class)))
                  .willReturn(comment);

        MoorayResult<Comment> result = commentService.createComment(1L, 1L, commentRequest);

        assertThat(result).isNotNull();

        BDDMockito.verify(taskRepository, times(1))
                  .findById(anyLong());
        BDDMockito.verify(commentRepository, times(1))
                  .save(any(Comment.class));
    }

    @DisplayName("특정 업무 내의 댓글 목록 조회")
    @Test
    void testRetrieveComments() {
        CommentCreationRequest commentRequest1 = CommentCreationRequest.sample();
        Comment comment1 = Comment.create(1L, task, commentRequest1);
        BDDMockito.lenient()
                  .when(commentRepository.save(any(Comment.class)))
                  .thenReturn(comment1);

        BDDMockito.lenient()
                  .when(commentRepository.findAll())
                  .thenReturn(List.of(comment1));

        MoorayResult<List<Comment>> result = commentService.retrieveComments(1L, 1L);

        assertThat(result).isNotNull();
        assertThat(result.getPayload()).containsEntry("data", List.of(comment1));
    }

    @DisplayName("특정 업무 내의 댓글 1건 조회")
    @Test
    void testRetrieveComment() {
        CommentCreationRequest commentRequest = CommentCreationRequest.sample();
        Comment comment = Comment.create(1L, task, commentRequest);
        BDDMockito.lenient()
                  .when(commentRepository.save(any(Comment.class)))
                  .thenReturn(comment);

        BDDMockito.lenient()
                  .when(commentRepository.findById(anyLong()))
                  .thenReturn(Optional.of(comment));

        MoorayResult<Comment> result = commentService.retrieveComment(1L, 1L, 1L);

        assertThat(result).isNotNull();
        assertThat(result.getPayload()).containsEntry("data", comment);
    }

    @DisplayName("특정 업무 내의 댓글 수정")
    @Test
    void testUpdateComment() {
        Comment originalComment = Comment.create(1L, task, CommentCreationRequest.sample());
        BDDMockito.lenient()
                  .when(commentRepository.save(any(Comment.class)))
                  .thenReturn(originalComment);

        BDDMockito.lenient()
                  .when(commentRepository.findById(1L))
                  .thenReturn(Optional.of(originalComment));

        CommentUpdateRequest updatedComment = spy(CommentUpdateRequest.sample());
        Comment comment = Comment.create(originalComment, updatedComment);

        BDDMockito.lenient()
                  .when(commentRepository.findById(1L))
                  .thenReturn(Optional.of(comment));

        MoorayResult<Comment> result = commentService.updateComment(1L, 1L, 1L, updatedComment);

        assertThat(result).isNotNull();
    }

    @DisplayName("특정 업무 내의 댓글 삭제")
    @Test
    void testDeleteComment() {
        CommentCreationRequest commentRequest = CommentCreationRequest.sample();
        Comment comment = Comment.create(1L, task, commentRequest);
        BDDMockito.lenient()
                  .when(commentRepository.save(any(Comment.class)))
                  .thenReturn(comment);

        BDDMockito.lenient()
                  .when(commentRepository.findById(1L))
                  .thenReturn(Optional.ofNullable(comment));

        MoorayResult<Boolean> result = commentService.deleteComment(1L, 1L, 1L);

        assertThat(result).isNotNull();
        assertThat(result.getPayload()).containsEntry("data", true);
    }

}