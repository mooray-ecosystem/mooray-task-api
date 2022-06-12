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
// ISSUE: No serializer found for class  and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77) at com.fasterxml.jackson.databind.SerializerProvider
@Getter
public class TaskCreationRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private LocalDate createdDate;

    @NotNull
    private LocalDate updatedDate;

    public static TaskCreationRequest sample() {
        return TaskCreationRequest.builder()
                                  .title("REST-API 설계 및 개발")
                                  .content("특강이 있어요.")
                                  .createdDate(now())
                                  .updatedDate(now())
                                  .build();
    }

}
