package com.example.kafa.kafka.common;

import lombok.Data;

/**
 * @author zhumingli
 * @create 2018-09-09 下午12:33
 * @desc
 **/
@Data
public class Response {

    private int code;

    private String message;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(int code) {
        this.code = code;
    }
}
