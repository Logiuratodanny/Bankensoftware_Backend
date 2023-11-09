package com.example.bankensoftware_backend.Repository;

import com.example.bankensoftware_backend.Entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByIban(String iban);
}
