package com.example.kafa.kafka.common;

/**
 * @author zhumingli
 * @create 2018-09-09 下午12:32
 * @desc
 **/
public enum ErrorCode {
    SuCCESS(200),
    EXCEPTIN(500);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
