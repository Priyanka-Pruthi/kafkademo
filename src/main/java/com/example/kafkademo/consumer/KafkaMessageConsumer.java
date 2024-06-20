package com.example.kafkademo.consumer;


import com.example.kafkademo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaMessageConsumer {


    private static final Logger log = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    @KafkaListener(topics = "users", groupId = "my-group-id")
    public void listen(String message) {
        log.info("Received message: " + message);
    }


    @KafkaListener(topics = {"${json.message.topic.name}"}, containerFactory = "kafkaListenerJsonFactory", groupId = "group_id")
    public void consumeSuperHero(User user) {
        log.info("**** -> Consumed User :: {}", user);
    }

}
