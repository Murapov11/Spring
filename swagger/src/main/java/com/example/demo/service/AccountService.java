package com.example.demo.service;
import com.example.demo.domain.dto.AccountCreateDTO;
import com.example.demo.domain.dto.AccountDTO;
import com.example.demo.domain.model.Account;
import com.example.demo.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }
    public AccountDTO createAccount(AccountCreateDTO accountCreateDTO) throws JsonProcessingException {
        Account account = new Account();
        account.setEmail(accountCreateDTO.getEmail());
        account.setPassword(accountCreateDTO.getPassword());
        account.setUsername(accountCreateDTO.getUsername());
        account.setLast_login(accountCreateDTO.getLoginDate());
        Account savedAccount = accountRepository.save(account);
        System.out.println(savedAccount);
        AccountDTO result = new AccountDTO(
                savedAccount.getUsername(),
                savedAccount.getPassword(),
                savedAccount.getEmail(),
                savedAccount.getLast_login());
        return result;
    }
}