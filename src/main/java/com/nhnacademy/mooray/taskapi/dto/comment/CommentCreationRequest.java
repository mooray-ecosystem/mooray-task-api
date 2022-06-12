package com.nhnacademy.mooray.taskapi.dto.comment;

import lombok.Getter;

@Getter
public class CommentCreationRequest {

    // TODO: validation
    private String author;
    private String content;

}
