package com.nhnacademy.mooray.taskapi.service;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentCreationRequest;

public interface CommentService {

    MoorayResult createComment(CommentCreationRequest commentRequest);

}
