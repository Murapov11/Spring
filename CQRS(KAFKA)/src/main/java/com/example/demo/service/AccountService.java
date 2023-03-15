package com.example.demo.service;

import com.example.demo.domain.dto.AccountCreateDTO;
import com.example.demo.domain.event.AccountCreatedEvent;
import com.example.demo.domain.event.AccountDeletedEvent;
import com.example.demo.domain.event.AccountUpdatedEvent;
import com.example.demo.domain.event.EmailUpdatedEvent;
import com.example.demo.domain.model.account.Account;
import com.example.demo.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    private final ApplicationEventPublisher eventPublisher;

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Account createAccount(AccountCreateDTO accountCreateDTO) throws JsonProcessingException {
        Account account = new Account();
        account.setEmail(accountCreateDTO.getEmail());
        account.setPassword(accountCreateDTO.getPassword());
        account.setUsername(accountCreateDTO.getUsername());
        Account savedAccount = accountRepository.save(account);

        AccountCreatedEvent event = new AccountCreatedEvent();
        event.setEmittedDate(LocalDateTime.now());
        event.setAggregateObjectType("Account");
        event.setAggregateObjectId(String.valueOf(savedAccount.getId()));

        ObjectMapper mapper = new ObjectMapper();
        event.setMessagePayload(mapper.writeValueAsString(savedAccount));

        eventPublisher.publishEvent(event);
        return savedAccount;
    }


    public void deleteAccount(long id) throws JsonProcessingException {
        accountRepository.deleteById(id);
        AccountDeletedEvent event = new AccountDeletedEvent();
        event.setEmittedDate(LocalDateTime.now());
        event.setAggregateObjectType("Account");
        event.setAggregateObjectId(String.valueOf(id));
        ObjectMapper mapper = new ObjectMapper();
        event.setMessagePayload(mapper.writeValueAsString("qwerty"));
        eventPublisher.publishEvent(event);
    }

    public Account updateAccount(long id,Account account) throws JsonProcessingException {
        Account updatedAccount = accountRepository.findById(id).get();

        updatedAccount.setUsername(account.getUsername());
        updatedAccount.setPassword(account.getPassword());
        updatedAccount.setEmail(account.getEmail());
        accountRepository.save(updatedAccount);
        AccountUpdatedEvent event = new AccountUpdatedEvent();
        event.setEmittedDate(LocalDateTime.now());
        event.setAggregateObjectType("Account");
        event.setAggregateObjectId(String.valueOf(id));
        ObjectMapper mapper = new ObjectMapper();
        event.setMessagePayload(mapper.writeValueAsString(updatedAccount));
        eventPublisher.publishEvent(event);

        EmailUpdatedEvent event_ = new EmailUpdatedEvent();
        event_.setEmittedDate(LocalDateTime.now());
        event_.setAggregateObjectType("Account");
        event_.setAggregateObjectId(String.valueOf(id));
        ObjectMapper mapper_ = new ObjectMapper();
        event_.setMessagePayload(mapper_.writeValueAsString(updatedAccount));
        eventPublisher.publishEvent(event_);
        return updatedAccount;
    }
    @KafkaListener(topicPartitions = @TopicPartition(topic = "Erkhan_topic", partitionOffsets = @PartitionOffset(partition= "0",initialOffset ="0"
            )))
    public void createEventListen(String message) {
        System.out.println("Received Message when CREATE event " + message);
    }
    @KafkaListener(topicPartitions = @TopicPartition(topic = "Erkhan_topic",
            partitionOffsets = @PartitionOffset(
                    partition= "1",initialOffset ="0"
            )))
    public void updateEventListen(String message) {
        System.out.println("Received Message when update event " + message);
    }
    @KafkaListener(topicPartitions = @TopicPartition(topic = "Erkhan_topic",
            partitionOffsets = @PartitionOffset(
                    partition= "2",initialOffset ="0"
            )))
    public void deleteEventListen(String message) {
        System.out.println("Received Message when delete event " + message);
    }
  }
