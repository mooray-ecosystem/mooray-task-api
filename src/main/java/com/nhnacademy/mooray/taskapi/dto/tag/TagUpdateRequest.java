package com.nhnacademy.mooray.taskapi.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TagUpdateRequest {

    @NotBlank
    private String name;

    public static TagUpdateRequest sample() {
        return new TagUpdateRequest("Scala");
    }

}
