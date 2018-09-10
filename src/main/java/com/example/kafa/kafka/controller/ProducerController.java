package com.example.kafa.kafka.controller;

import com.example.kafa.kafka.common.ErrorCode;
import com.example.kafa.kafka.common.MessageEntity;
import com.example.kafa.kafka.common.Response;
import com.example.kafa.kafka.producer.SimpleProducer;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhumingli
 * @create 2018-09-09 下午1:52
 * @desc
 **/
@Slf4j
@RestController
@RequestMapping("/kafka")
public class ProducerController {

    @Autowired
    private SimpleProducer simpleProducer;

    @Value("${spring.kafka.topic.default}")
    private String topic;

    private Gson gson = new Gson();

    @GetMapping("/hello")
    public Response sendKafka(){
        return new Response(ErrorCode.SuCCESS.getCode(),"测试OK");
    }

    @RequestMapping(value = "/send",method = RequestMethod.POST,produces = {"application/json"})
    public Response sendKafka(@RequestBody MessageEntity messageEntity){

        try{
            log.info("kafka的消息={}", messageEntity);
            simpleProducer.send(topic,messageEntity);
            log.info("kafka发送成功");
            return new Response(ErrorCode.SuCCESS.getCode(),"发送成功");
        }
        catch (Exception e){
            log.info("kafka发送失败");
            e.printStackTrace();
            return new Response(ErrorCode.EXCEPTIN.getCode(),"发送失败");
        }

    }

}
