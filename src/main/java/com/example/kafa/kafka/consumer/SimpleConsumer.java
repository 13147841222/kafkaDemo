package com.example.kafa.kafka.consumer;

import com.example.kafa.kafka.common.MessageEntity;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zhumingli
 * @create 2018-09-09 下午1:48
 * @desc
 **/
@Slf4j
@Component
public class SimpleConsumer {
    private final Gson gson = new Gson();


    @KafkaListener(topics = "${spring.kafka.topic.default}" ,containerFactory = "kafkaListenerContainerFactory")
    public void receive(MessageEntity messageEntity){

        log.info(gson.toJson(messageEntity));
    }
}
