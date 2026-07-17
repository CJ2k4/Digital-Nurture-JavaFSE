package com.digitalnurture.spring.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    void addReturnsSum() {
        assertEquals(7, calculatorService.add(3, 4));
    }
}
