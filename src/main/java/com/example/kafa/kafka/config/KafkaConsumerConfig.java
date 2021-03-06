package com.example.kafa.kafka.config;

import com.example.kafa.kafka.common.MessageEntity;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.serializer.Deserializer;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhumingli
 * @create 2018-09-09 上午11:43
 * @desc
 **/
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.consumer.servers}")
    private String server;

    @Value("${spring.kafka.consumer.enable.auto.commit}")
    private boolean enableAutoCommit;

    @Value("${spring.kafka.consumer.session.timeout}")
    private String sessionTimeout;

    @Value("${spring.kafka.consumer.auto.commit.interval}")
    private String autoCommitInterval;

    @Value("${spring.kafka.consumer.group.id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto.offset.reset}")
    private String autoOffsetReset;

    @Value("${spring.kafka.consumer.concurrency}")
    private int concurrency;


    private Map<String, Object> consumerConfigs(){
        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,server);
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,enableAutoCommit);
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,sessionTimeout);
        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,autoCommitInterval);
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,autoOffsetReset);
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return propsMap;
    }

    private ConsumerFactory<String , MessageEntity> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(MessageEntity.class)
        );
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MessageEntity>> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, MessageEntity> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(concurrency);
        factory.getContainerProperties().setPollTimeout(1500);
        return factory;
    }




}
