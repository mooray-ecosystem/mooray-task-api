package com.nhnacademy.mooray.taskapi.exception;

public class NotFoundTagException extends RuntimeException {

    public NotFoundTagException() {
        super("태그가 존재하지 않습니다.");
    }

}
