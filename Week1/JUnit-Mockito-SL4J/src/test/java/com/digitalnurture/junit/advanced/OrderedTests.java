package com.digitalnurture.junit.advanced;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderedTests {

    private static int counter = 0;

    @Test
    @Order(1)
    void firstTest() {
        counter = 1;
        assertEquals(1, counter);
    }

    @Test
    @Order(2)
    void secondTest() {
        counter = 2;
        assertEquals(2, counter);
    }

    @Test
    @Order(3)
    void thirdTest() {
        assertEquals(2, counter);
    }
}
