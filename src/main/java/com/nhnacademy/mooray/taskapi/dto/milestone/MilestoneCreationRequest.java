package com.nhnacademy.mooray.taskapi.dto.milestone;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
public class MilestoneCreationRequest {

    @NotBlank
    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

}
