package com.nhnacademy.mooray.taskapi.dto.project;

import com.nhnacademy.mooray.taskapi.domain.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Getter
public class ProjectUpdateRequest {

    @NotNull
    private final Long adminId;

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    @NotNull
    private final String status;

    public static ProjectUpdateRequest sample() {
        return ProjectUpdateRequest.builder()
                                   .adminId(1L)
                                   .name("2022-03-김해-웹서비스개발-1기")
                                   .description("프로젝트 설명글이 있습니다.")
                                   .status(ProjectStatus.ARCHIVED.name())
                                   .build();
    }

}
