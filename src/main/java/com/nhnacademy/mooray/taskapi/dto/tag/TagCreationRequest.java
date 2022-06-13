package com.nhnacademy.mooray.taskapi.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class TagCreationRequest {

    @NotBlank
    private String name;

    public static TagCreationRequest sample() {
        return new TagCreationRequest("Java");
    }

}
