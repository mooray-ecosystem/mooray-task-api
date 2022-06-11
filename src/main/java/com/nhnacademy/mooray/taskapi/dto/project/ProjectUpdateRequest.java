package com.nhnacademy.mooray.taskapi.dto.project;

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

}
