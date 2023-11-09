package com.example.bankensoftware_backend.Controller;

import com.example.bankensoftware_backend.Entity.Account;
import com.example.bankensoftware_backend.Entity.Customer;
import com.example.bankensoftware_backend.Repository.AccountRepository;
import com.example.bankensoftware_backend.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/customer")
    public List<Customer> loadAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @GetMapping("/customer/{id}")
    public Optional<Customer> loadbyId(@PathVariable Long id) {
        return customerRepository.findById(id);
    }

    @GetMapping("/customer/{id}/accounts")
    public List<Account> getAccountsFromCustomer(@PathVariable int id) {
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        return accounts.stream()
                .filter(account -> account.getCustomer().getId() == id)
                .toList();
    }
}
