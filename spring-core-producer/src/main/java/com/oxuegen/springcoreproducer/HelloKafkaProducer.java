package com.oxuegen.springcoreproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

public class HelloKafkaProducer {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String name){
        kafkaTemplate.send("t-hello", "Hello " + name);
    }

}
