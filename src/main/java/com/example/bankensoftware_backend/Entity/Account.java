package com.example.bankensoftware_backend.Entity;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IBAN")
    private String iban;

    @Column(name = "BALANCE")
    private double balance;

    @Column(name = "OVERDRAFT")
    private double overdraft;

    @ManyToOne
    @JoinColumn(name="CUSTOMER_ID", nullable=false)
    private Customer customer;

    public Account(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
