package com.example.kafa.kafka.producer;

import com.example.kafa.kafka.common.MessageEntity;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author zhumingli
 * @create 2018-09-09 下午12:47
 * @desc
 **/
@Component
public class SimpleProducer {

    @Autowired
    @Qualifier("kafkaTemplate")
    private KafkaTemplate<String, MessageEntity> kafkaTemplate;

    public void send(String topic, MessageEntity messageEntity){
        kafkaTemplate.send(topic,messageEntity);
    }

    public void send(String topic, String key, MessageEntity messageEntity){
        ProducerRecord<String, MessageEntity> producerRecord = new ProducerRecord<>(topic,key,messageEntity);
        long startTime = System.currentTimeMillis();
        ListenableFuture<SendResult<String, MessageEntity>> future = kafkaTemplate.send(producerRecord);
        future.addCallback(new ProducerCallback(startTime,key,messageEntity));
    }


}
