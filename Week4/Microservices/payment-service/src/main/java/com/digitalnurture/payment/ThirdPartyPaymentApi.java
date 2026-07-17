package com.digitalnurture.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyPaymentApi {

    private static final Logger log = LoggerFactory.getLogger(ThirdPartyPaymentApi.class);

    public String processPayment(double amount) {
        log.info("Calling slow third-party API for amount {}", amount);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (amount > 10000) {
            throw new RuntimeException("Third-party API timeout");
        }
        return "Payment of " + amount + " processed";
    }
}
