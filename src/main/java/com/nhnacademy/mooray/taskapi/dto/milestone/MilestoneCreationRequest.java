package com.nhnacademy.mooray.taskapi.dto.milestone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
public class MilestoneCreationRequest {

    @NotBlank
    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    public static MilestoneCreationRequest sample() {
        return MilestoneCreationRequest.builder()
                                       .title("출석")
                                       .startDate(LocalDate.of(2022, 3, 1))
                                       .endDate(LocalDate.of(2022, 9, 30))
                                       .build();
    }

}
