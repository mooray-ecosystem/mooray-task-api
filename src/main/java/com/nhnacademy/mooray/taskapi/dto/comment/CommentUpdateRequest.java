package com.nhnacademy.mooray.taskapi.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static java.time.LocalDate.now;

@AllArgsConstructor
@Builder
@Getter
public class CommentUpdateRequest {

    @NotBlank
    private final String author;

    @NotBlank
    private final String content;

    @NotNull
    private final LocalDate updatedDate;

    public static CommentUpdateRequest sample() {
        return new CommentUpdateRequest("CoRock", "감사합니다.", now());
    }

}
