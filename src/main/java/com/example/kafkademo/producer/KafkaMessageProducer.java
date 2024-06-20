package com.example.kafkademo.producer;

import com.example.kafkademo.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaMessageProducer {
    @Value("${topic.name}")
    private String topic;

    @Value("${json.message.topic.name}")
    private String users;

    private static final Logger log = LoggerFactory.getLogger(KafkaMessageProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;


    private final KafkaTemplate<String, User> kafkaJsonTemplate;

    public KafkaMessageProducer(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String,User> kafkaJsonTemplate ) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaJsonTemplate = kafkaJsonTemplate;
    }

    public void sendMessage(String message) {

        kafkaTemplate.send(topic, message);
        log.info("Message : {} sent to topic : {}",message,topic);
    }

    public void sendJsonMessage(User user)
    {
        kafkaJsonTemplate.send(users,user);
        log.info("Message : {} sent to topic : {}",user.toString(),topic);
    }
}
