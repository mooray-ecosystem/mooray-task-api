package com.nhnacademy.mooray.taskapi.dto.task;

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
public class TaskUpdateRequest {

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

    @NotNull
    private final LocalDate updatedDate;

    public static TaskUpdateRequest sample() {
        return TaskUpdateRequest.builder()
                                .title("파이널 프로젝트")
                                .content("저희와 함께 갑시다.")
                                .updatedDate(now())
                                .build();
    }

}
