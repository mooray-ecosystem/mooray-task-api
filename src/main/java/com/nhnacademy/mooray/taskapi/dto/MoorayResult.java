package com.nhnacademy.mooray.taskapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MoorayResult<V> {

    // private static final Map<String, Object> header;
    private final boolean isSuccess;
    private final String message;

    private final Map<String, V> payload;

    public MoorayResult(boolean isSuccess, String message, Map<String, V> payload) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.payload = payload;
    }

    public static MoorayResult fail(String message) {
        return new MoorayResult(false, message, null);
    }

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

    public static <V> MoorayResult<V> success(String message, Map<String, V> payload) {
        return new MoorayResult<>(true, message, payload);
    }

    public static Map<String, Object> header() {
        return new HashMap<>();
    }

}
