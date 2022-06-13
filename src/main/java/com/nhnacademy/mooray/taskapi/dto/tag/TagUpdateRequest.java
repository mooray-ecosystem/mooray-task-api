package com.nhnacademy.mooray.taskapi.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class TagUpdateRequest {

    @NotBlank
    private final String name;

    public static TagCreationRequest sample() {
        return new TagCreationRequest("Scala");
    }

}
