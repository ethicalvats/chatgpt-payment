package com.example.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class MockPaymentGatewayController {

    @PostMapping("/mock")
    public ResponseEntity<String> mock() {
        // generate a random boolean value
        boolean isSuccess = Math.random() < 0.5;
        // construct redirect URL based on the random boolean value
        String redirectUrl = isSuccess ? "/success" : "/failure";
        // redirect to the URL
        System.out.println("Payment redirect:: "+redirectUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(
                URI.create("http://localhost:8080"+redirectUrl)).build();
    }
}

