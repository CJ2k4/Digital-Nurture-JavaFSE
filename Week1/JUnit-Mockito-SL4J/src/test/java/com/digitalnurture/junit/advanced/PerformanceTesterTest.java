package com.digitalnurture.junit.advanced;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

class PerformanceTesterTest {

    private final PerformanceTester performanceTester = new PerformanceTester();

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void performTaskCompletesWithinTimeout() throws InterruptedException {
        performanceTester.performTask();
    }
}
