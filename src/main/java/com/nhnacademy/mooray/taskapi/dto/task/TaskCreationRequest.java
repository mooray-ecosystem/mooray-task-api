package com.nhnacademy.mooray.taskapi.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TaskCreationRequest {

    private String title;
    private String content;

    public static TaskCreationRequest sample() {
        return new TaskCreationRequest("REST-API 설계 및 개발", "특강이 있어요.");
    }
}
