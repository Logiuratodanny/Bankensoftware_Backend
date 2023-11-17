package com.example.bankensoftware_backend.Controller;


import com.example.bankensoftware_backend.Entity.Account;
import com.example.bankensoftware_backend.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;


    @GetMapping("/account")
    public List<Account> loadAll() {
        return (List<Account>) accountRepository.findAll();
    }


    @PostMapping("/account/deposit/{id}/{amount}")
    public ResponseEntity<?> deposit(@PathVariable Long id, @PathVariable Float amount) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent() && amount > 0) {
            Account account = accountOptional.get();
            account.setBalance((account.getBalance() + amount));
            accountRepository.save(account);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/account/payout/{id}/{amount}")
    public ResponseEntity<?> payout(@PathVariable Long id, @PathVariable Float amount) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent() && amount > 0) {
            Account account = accountOptional.get();
            account.setBalance((account.getBalance() - amount));
            accountRepository.save(account);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/account/transfer/{id}/{amount}/{iban}")
    public ResponseEntity<?> transfer(@PathVariable Long id, @PathVariable Float amount, @PathVariable String iban) {
        Optional<Account> accountOptionalFrom = accountRepository.findById(id);
        Optional<Account> accountOptionalTo = accountRepository.findByIban(iban);

        if (accountOptionalFrom.isPresent() && accountOptionalTo.isPresent()) {
            Account accountFrom = accountOptionalFrom.get();
            Account accountTo = accountOptionalTo.get();

            if (accountFrom.getBalance() >= amount) {
                accountFrom.setBalance(accountFrom.getBalance() - amount);
                accountTo.setBalance(accountTo.getBalance() + amount);
                accountRepository.save(accountFrom);
                accountRepository.save(accountTo);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("Insufficient balance for the transfer.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
