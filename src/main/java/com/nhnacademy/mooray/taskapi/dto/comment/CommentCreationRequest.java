package com.nhnacademy.mooray.taskapi.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentCreationRequest {

    // TODO: validation
    private String author;
    private String content;

    public static CommentCreationRequest sample() {
        return new CommentCreationRequest("CoRock", "감사합니다.");
    }

}
