package com.example.kafa.kafka.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author zhumingli
 * @create 2018-09-09 下午12:29
 * @desc
 **/
@Data
@EqualsAndHashCode
@ToString
public class MessageEntity {

    private String title;

    private String body;


}
