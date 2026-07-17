package com.digitalnurture.junit.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void addReturnsSum() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void subtractReturnsDifference() {
        assertEquals(1, calculator.subtract(3, 2));
    }

    @Test
    void multiplyReturnsProduct() {
        assertEquals(6, calculator.multiply(2, 3));
    }

    @Test
    void divideReturnsQuotient() {
        assertEquals(2.0, calculator.divide(6, 3));
    }

    @Test
    void divideByZeroThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(1, 0));
    }
}
