package com.nhnacademy.mooray.taskapi.exception;

public class NotFoundMilestoneException extends RuntimeException {

    public NotFoundMilestoneException() {
        super("마일스톤이 존재하지 않습니다.");
    }

}
