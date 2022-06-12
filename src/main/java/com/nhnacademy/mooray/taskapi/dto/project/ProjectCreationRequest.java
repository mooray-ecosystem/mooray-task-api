package com.nhnacademy.mooray.taskapi.dto.project;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Getter
public class ProjectCreationRequest {

    @NotNull
    private final Long adminId;

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    @NotNull
    private final String status;

    public static ProjectCreationRequest sample() {
        return ProjectCreationRequest.builder()
                                     .adminId(1L)
                                     .name("2022-03-김해-웹서비스개발-1기")
                                     .description("김해 우수 인재 육성을 위한 프로젝트입니다.")
                                     .status("ACTIVATED")
                                     .build();
    }

}
