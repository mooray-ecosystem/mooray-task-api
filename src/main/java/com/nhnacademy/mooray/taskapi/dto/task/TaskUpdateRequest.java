package com.nhnacademy.mooray.taskapi.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TaskUpdateRequest {

    private final String title;
    private final String content;

}
