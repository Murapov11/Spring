package com.example.demo.domain.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmailUpdatedEvent {
    String aggregateObjectType;
    String aggregateObjectId;
    String messagePayload;
    LocalDateTime emittedDate;
}