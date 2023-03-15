package com.example.demo.service;

import com.example.demo.domain.event.AccountCreatedEvent;
import com.example.demo.domain.event.AccountDeletedEvent;
import com.example.demo.domain.event.AccountUpdatedEvent;
import com.example.demo.domain.event.EmailUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;
    /*
        @EventListener
    public void processCreate(AccountCreatedEvent event) {
        log.info("event received: " + event);
        kafkaTemplate.send("New_topic",
                Partitions.PARTITION_CREATE,
                event.getAggregateObjectId(),
                event.toString());
    }
     */

    @EventListener
    public void process(AccountCreatedEvent event) {
        log.info("event received: " + event);
        kafkaTemplate.send("Erkhan_topic", 0, event.getAggregateObjectId(),event.toString());
    }
    @EventListener
    public void process(AccountDeletedEvent event){
        log.info("event received: " + event);
        kafkaTemplate.send("Erkhan_topic",2, event.getAggregateObjectId(),event.toString());
    }
    @EventListener
    public void process(AccountUpdatedEvent event){
        log.info("event received: " + event);
        kafkaTemplate.send("Erkhan_topic",1 ,event.getAggregateObjectId(),event.toString());
    }
    @EventListener
    public void process(EmailUpdatedEvent event){
        log.info("event received: " + event);
        kafkaTemplate.send("Erkhan_topic",3 ,event.getAggregateObjectId(),event.toString());
    }
}
