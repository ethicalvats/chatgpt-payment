package com.example.api.Repository;

import com.example.api.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PaymentRepository extends ReactiveCrudRepository<Payment, String> {

    Mono<Payment> findByOrderIdAndTransactionType(String orderId, String transactionType);

}