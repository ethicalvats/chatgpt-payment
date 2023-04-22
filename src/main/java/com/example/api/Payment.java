package com.example.api;

import lombok.Data;

@Data
public class Payment {
    private String id;
    private double amount;
    private String currency;
    private String description;
    private String orderId;
    private String transactionType;
    private String transactionId;

    // getters and setters
}
