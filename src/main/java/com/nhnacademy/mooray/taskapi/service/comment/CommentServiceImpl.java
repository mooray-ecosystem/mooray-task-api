package com.nhnacademy.mooray.taskapi.service.comment;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Comment;
import com.nhnacademy.mooray.taskapi.entity.Task;
import com.nhnacademy.mooray.taskapi.repository.CommentRepository;
import com.nhnacademy.mooray.taskapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    @Transactional
    @Override
    public MoorayResult<Comment> createComment(Long projectId, Long taskId, CommentCreationRequest commentRequest) {
        Task foundTask = taskRepository.findById(taskId)
                                       .orElseThrow(RuntimeException::new);

        Comment comment = Comment.create(foundTask, commentRequest);
        Comment savedComment = commentRepository.save(comment);

        Map<String, Comment> payload = new HashMap<>();
        payload.put("data", savedComment);

        return MoorayResult.success("성공적으로 댓글을 작성했습니다.", payload);
    }

    @Override
    public MoorayResult retrieveComments(Long projectId, Long taskId) {
        List<Comment> comments = commentRepository.findAll();

        Map<String, Object> payload = new HashMap<>();
        payload.put("data", comments);

        return MoorayResult.success("성공적으로 프로젝트 댓글 목록을 불러왔습니다.", payload);
    }

    @Override
    public MoorayResult retrieveComment(Long projectId, Long taskId, Long commentId) {
        // commentRepository.findById()
        return null;
    }

    @Transactional
    @Override
    public MoorayResult updateComment(Long projectId, Long taskId, Long commentId,
                                      CommentUpdateRequest commentRequest) {
        return null;
    }

    @Transactional
    @Override
    public MoorayResult deleteComment(Long projectId, Long taskId, Long commentId) {
        return null;
    }

}
