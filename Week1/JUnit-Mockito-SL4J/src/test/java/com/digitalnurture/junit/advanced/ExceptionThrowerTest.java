package com.digitalnurture.junit.advanced;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ExceptionThrowerTest {

    private final ExceptionThrower exceptionThrower = new ExceptionThrower();

    @Test
    void throwExceptionThrowsIllegalStateException() {
        assertThrows(IllegalStateException.class, exceptionThrower::throwException);
    }
}
