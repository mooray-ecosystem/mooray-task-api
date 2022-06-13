package com.nhnacademy.mooray.taskapi.exception;

public class NotFoundTaskException extends RuntimeException {

    public NotFoundTaskException() {
        super("업무가 존재하지 않습니다.");
    }

}
