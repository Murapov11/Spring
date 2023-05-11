package com.example.demo.controller;

import com.example.demo.domain.dto.AccountCreateDTO;
import com.example.demo.domain.dto.AccountDTO;
import com.example.demo.domain.model.Account;
import com.example.demo.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/user/accounts")
    public List<Account> findAll() {
        return accountService.findAllAccounts();
    }

    @PostMapping("/user/accounts")
    public AccountDTO createAccount(@RequestBody AccountCreateDTO accountCreateDTO) throws JsonProcessingException {
        return accountService.createAccount(accountCreateDTO);
    }
}