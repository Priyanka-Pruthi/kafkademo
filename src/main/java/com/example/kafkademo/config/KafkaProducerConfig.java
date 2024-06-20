package com.example.kafkademo.config;

import com.example.kafkademo.model.User;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_server;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_server);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Primary
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    //JSON Producer

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Json Producer
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Bean
    public ProducerFactory<String, User> producerJsonFactory() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_server);
        configProps.put(ProducerConfig.ACKS_CONFIG, "all");
        configProps.put(ProducerConfig.RETRIES_CONFIG, 3);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // **___ configure the following three settings for SSL Encryption ___**
        //configProps.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
        //configProps.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "/var/private/ssl/kafka.client.truststore.jks");
        //configProps.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "test1234");

        // **___configure the following three settings for SSL Authentication ___**
        //configProps.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "/var/private/ssl/kafka.client.keystore.jks");
        //configProps.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "test1234");
        //configProps.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "test1234");

        return new DefaultKafkaProducerFactory<>(configProps);
    }




    @Bean
    public  KafkaTemplate<String, User> kafkaJsonTemplate() {
        return new KafkaTemplate<>(producerJsonFactory());
    }

}
