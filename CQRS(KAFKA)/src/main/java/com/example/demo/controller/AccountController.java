package com.example.demo.controller;

import com.example.demo.domain.dto.AccountCreateDTO;
import com.example.demo.domain.model.account.Account;
import com.example.demo.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class AccountController {
    private final AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> findAll() {
        return accountService.findAllAccounts();
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody AccountCreateDTO accountCreateDTO) throws JsonProcessingException {
        return accountService.createAccount(accountCreateDTO);
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable("id") long id) throws JsonProcessingException {
        accountService.deleteAccount(id);
    }

    @PutMapping("/accounts/{id}")
    public Account updateAccount(@PathVariable("id") long id, @RequestBody Account account) throws JsonProcessingException {
        return accountService.updateAccount(id, account);
    }

}
