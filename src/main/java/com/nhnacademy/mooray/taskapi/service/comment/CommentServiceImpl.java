package com.nhnacademy.mooray.taskapi.service.comment;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Comment;
import com.nhnacademy.mooray.taskapi.entity.Task;
import com.nhnacademy.mooray.taskapi.repository.CommentRepository;
import com.nhnacademy.mooray.taskapi.repository.ProjectRepository;
import com.nhnacademy.mooray.taskapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    @Override
    public MoorayResult<Comment> createComment(Long projectId, Long taskId, CommentCreationRequest commentRequest) {
        // FIXME: Refactor logging
        log.error("c.n.mooray.taskapi.service.comment.CommentServiceImpl: Enter createComment(...)");

        // if (taskRepository.existsById(taskId)) {
        //     return MoorayResult.fail("댓글이 이미 존재합니다.");
        // }

        Task foundTask = taskRepository.findById(taskId)
                                       .orElseThrow(RuntimeException::new);

        Comment comment = Comment.create(projectId, foundTask, commentRequest);
        Comment savedComment = commentRepository.save(comment);

        Map<String, Comment> payload = new HashMap<>();
        payload.put("data", savedComment);

        return MoorayResult.success("성공적으로 댓글을 작성했습니다.", payload);
    }

    @Override
    public MoorayResult<List<Comment>> retrieveComments(Long projectId, Long taskId) {
        // FIXME: Refactor logging
        log.error("c.n.mooray.taskapi.service.comment.CommentServiceImpl: Enter retrieveComments()");

        List<Comment> comments = commentRepository.findAll();

        Map<String, List<Comment>> payload = new HashMap<>();
        payload.put("data", comments);

        return MoorayResult.success("성공적으로 프로젝트 댓글 목록을 불러왔습니다.", payload);
    }

    @Override
    public MoorayResult<Comment> retrieveComment(Long projectId, Long taskId, Long commentId) {
        // FIXME: Refactor logging
        log.error("c.n.mooray.taskapi.service.comment.CommentServiceImpl: Enter retrieveComment(...)");

        Comment comment = commentRepository.findById(commentId)
                                           .orElseThrow(RuntimeException::new);

        Map<String, Comment> payload = new HashMap<>();
        payload.put("data", comment);

        return MoorayResult.success("성공적으로 댓글을 불러왔습니다.", payload);
    }

    @Transactional
    @Override
    public MoorayResult<Comment> updateComment(Long projectId, Long taskId, Long commentId,
                                               CommentUpdateRequest commentRequest) {
        // FIXME: Refactor logging
        log.error("c.n.mooray.taskapi.service.comment.CommentServiceImpl: Enter updateComment(...)");

        // 1. 수정할 댓글을 아이디로 찾는다.
        Comment foundComment = commentRepository.findById(commentId)
                                                .orElseThrow(RuntimeException::new);


        // 2. 불변 객체를 새로 만들 때 원본과 바꿀 요청 데이터를 같이 던져준다.
        Comment updatedComment = Comment.create(foundComment, commentRequest);

        // 3. 업데이트 친다.
        Comment savedComment = commentRepository.save(updatedComment);

        Map<String, Comment> payload = new HashMap<>();
        payload.put("data", savedComment);

        return MoorayResult.success("", payload);
    }

    @Transactional
    @Override
    public MoorayResult<Boolean> deleteComment(Long projectId, Long taskId, Long commentId) {
        // FIXME: Refactor logging
        log.error("c.n.mooray.taskapi.service.comment.CommentServiceImpl: Enter deleteComment(...)");

        commentRepository.deleteById(commentId);

        Map<String, Boolean> payload = new HashMap<>();
        payload.put("data", true);

        return MoorayResult.success("", payload);
    }

}
