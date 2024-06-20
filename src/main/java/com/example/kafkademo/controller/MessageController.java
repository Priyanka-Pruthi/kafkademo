package com.example.kafkademo.controller;

import com.example.kafkademo.model.User;
import com.example.kafkademo.producer.KafkaMessageProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final KafkaMessageProducer messageProducer;

    public MessageController(KafkaMessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
        messageProducer.sendMessage(message);
        return "Message sent: " + message;
    }

    @PostMapping("/publish")
    public String sendMessage(@RequestBody User user) {
        messageProducer.sendJsonMessage(user);
        return "Message sent to Kafka topic";
    }
}
