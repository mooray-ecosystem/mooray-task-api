package com.nhnacademy.mooray.taskapi.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class CommentCreationRequest {

    @NotBlank
    private String author;

    @NotBlank
    private String content;

    public static CommentCreationRequest sample() {
        return new CommentCreationRequest("CoRock", "감사합니다.");
    }

}
