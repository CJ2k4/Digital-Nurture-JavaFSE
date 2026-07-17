package com.digitalnurture.payment;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private ThirdPartyPaymentApi thirdPartyPaymentApi;

    @CircuitBreaker(name = "paymentCircuitBreaker", fallbackMethod = "fallback")
    public String pay(double amount) {
        return thirdPartyPaymentApi.processPayment(amount);
    }

    public String fallback(double amount, Throwable throwable) {
        log.warn("Fallback triggered for amount {}: {}", amount, throwable.getMessage());
        return "Payment queued for manual processing (amount: " + amount + ")";
    }
}
