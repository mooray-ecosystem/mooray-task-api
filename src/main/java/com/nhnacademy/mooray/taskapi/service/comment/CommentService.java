package com.nhnacademy.mooray.taskapi.service.comment;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Comment;

import java.util.List;

public interface CommentService {

    MoorayResult<Comment> createComment(Long projectId, Long taskId, CommentCreationRequest commentRequest);

    MoorayResult<List<Comment>> retrieveComments(Long projectId, Long taskId);

    MoorayResult<Comment> retrieveComment(Long projectId, Long taskId, Long commentId);

    MoorayResult<Comment> updateComment(Long projectId, Long taskId, Long commentId, CommentUpdateRequest commentRequest);

    MoorayResult<Boolean> deleteComment(Long projectId, Long taskId, Long commentId);

}
