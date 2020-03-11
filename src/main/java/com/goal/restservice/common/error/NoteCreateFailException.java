package com.goal.restservice.common.error;

import com.goal.restservice.common.errorcode.ErrorCode;

public class NoteCreateFailException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    ErrorCode errorCode;

    public NoteCreateFailException() { errorCode = ErrorCode.NOTE_CREATE_FAIL; }

    public ErrorCode getErrorCode() { return errorCode; }
}
