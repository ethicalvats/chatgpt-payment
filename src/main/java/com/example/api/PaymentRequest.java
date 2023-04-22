package com.example.api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private String transactionId;
    private double amount;
    private String currency;
    private String description;
    private String httpMethod;
    private String callbackUrl;

    // constructor, getters and setters
}