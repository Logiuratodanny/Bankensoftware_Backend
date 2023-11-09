package com.example.bankensoftware_backend.Repository;


import com.example.bankensoftware_backend.Entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
