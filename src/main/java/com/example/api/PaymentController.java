package com.example.api;

import com.example.api.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private WebClient webClient;

    @PostMapping
    public Mono<ResponseEntity<String>> initiatePayment(@RequestBody Payment payment) {
        // check if transaction type is not present for the same order ID
        Mono<Payment> existingPayment = paymentRepository.findByOrderIdAndTransactionType(payment.getOrderId(), payment.getTransactionType());
        return existingPayment.flatMap(p -> {
            String errorMessage = "Transaction type already exists for this order ID.";
            return Mono.just(ResponseEntity.badRequest().body(errorMessage));
        }).switchIfEmpty(
                // generate unique transaction ID
                Mono.just(UUID.randomUUID().toString()).flatMap(transactionId -> {
                    payment.setTransactionId(transactionId);
                    // save payment to database
                    return paymentRepository.save(payment);
                }).flatMap(savedPayment -> {
                    // make call to another API to send payment details
                    String apiUrl = "http://localhost:8080/api/mock";
                    PaymentRequest paymentRequest = new PaymentRequest(savedPayment.getTransactionId(), savedPayment.getAmount(), "INR", savedPayment.getDescription(), "POST", "https://example.com/callback");
                    return webClient.post()
                            .uri(apiUrl)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(Mono.just(paymentRequest), PaymentRequest.class)
                            .retrieve()
                            .bodyToMono(String.class);
                }).map(response -> ResponseEntity.ok("Payment initiated successfully."))
        );
    }
}

