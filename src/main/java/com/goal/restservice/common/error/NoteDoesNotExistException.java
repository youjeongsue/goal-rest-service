package com.goal.restservice.common.error;

import com.goal.restservice.common.errorcode.ErrorCode;

public class NoteDoesNotExistException extends RuntimeException{

    private static final long serialVersion = 1L;

    ErrorCode errorCode;

    public NoteDoesNotExistException() {
        errorCode = ErrorCode.NOTE_DOES_NOT_EXIST;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
