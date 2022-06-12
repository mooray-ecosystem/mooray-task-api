package com.nhnacademy.mooray.taskapi.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class TaskUpdateRequest {

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

}
