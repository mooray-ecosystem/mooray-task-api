package com.nhnacademy.mooray.taskapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public class MoorayResult {

    // private static final Map<String, Object> header;
    // private final Map<String, Object> payload;

    private final boolean isSuccess;
    private final String message;

    // public MoorayResult() {
    //     this.header = new HashMap<>();
    //     this.header.put("isSuccess", false);
    //     this.header.put("message", "");
    //
    //     this.payload = new HashMap<>();
    // }

    // ISSUE: https://stackoverflow.com/questions/32270422/jackson-renames-primitive-boolean-field-by-removing-is
    @JsonProperty(value = "isSuccess")
    public boolean isSuccess() {
        return isSuccess;
    }

    public static MoorayResult success() {
        return new MoorayResult(true, "프로젝트를 성공적으로 생성했습니다.");
    }

    public static Map<String, Object> header() {
        return new HashMap<>();
    }

}
