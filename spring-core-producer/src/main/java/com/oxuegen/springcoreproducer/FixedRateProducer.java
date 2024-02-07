package com.oxuegen.springcoreproducer;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;


public class FixedRateProducer {

    private static final Logger LOG = (Logger) LoggerFactory.getLogger(FixedRateProducer.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private AtomicInteger counter = new AtomicInteger();

    @Scheduled(fixedRate = 1000)
    public void sendMessage(){
        var i = counter.incrementAndGet();
        LOG.info("i is " + i);
        kafkaTemplate.send("t-fixedrate", "Fixed rate " + i);
    }
}
