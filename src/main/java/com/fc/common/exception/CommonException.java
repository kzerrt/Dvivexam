package com.fc.common.exception;

import com.fc.pojo.result.CodeMsg;

/**
 * @author Florence
 * @since 2023/04/29
 */


public class CommonException extends RuntimeException{
    private int code;

    public CommonException() {
        this(CodeMsg.BAD_REQUEST.code(), null);
    }
    public CommonException(String msg) {
        this(msg, null);
    }
    public CommonException(int code, String msg) {
        this(code, msg, null);
    }
    public CommonException(String msg, Throwable cause) {
        this(CodeMsg.BAD_REQUEST.code(), msg, cause);
    }
    public CommonException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }
    public CommonException(CodeMsg codeMsg) {
        this(codeMsg.code(), codeMsg.msg(), null);
    }
    public CommonException(CodeMsg codeMsg, Throwable cause) {
        this(codeMsg.code(), codeMsg.msg(), cause);
    }

    public int getCode() {
        return code;
    }
}
