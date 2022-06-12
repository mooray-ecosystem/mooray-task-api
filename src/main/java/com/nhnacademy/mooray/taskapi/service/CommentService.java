package com.nhnacademy.mooray.taskapi.service;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentUpdateRequest;

public interface CommentService {

    MoorayResult createComment(Long projectId, Long taskId, CommentCreationRequest commentRequest);

    MoorayResult retrieveComments(Long projectId, Long taskId);

    MoorayResult retrieveComment(Long projectId, Long taskId, Long commentId);

    MoorayResult updateComment(Long projectId, Long taskId, Long commentId, CommentUpdateRequest commentRequest);

    MoorayResult deleteComment(Long projectId, Long taskId, Long commentId);

}
