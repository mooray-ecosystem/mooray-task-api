package com.nhnacademy.mooray.taskapi.dto.milestone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
public class MilestoneUpdateRequest {

    @NotBlank
    private final String title;

    private final LocalDate startDate;

    private final LocalDate endDate;

    public static MilestoneUpdateRequest sample() {
        return MilestoneUpdateRequest.builder()
                                     .title("교육")
                                     .startDate(LocalDate.of(2022, 3, 4))
                                     .endDate(LocalDate.of(2022, 9, 16))
                                     .build();
    }

}
