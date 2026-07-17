package com.digitalnurture.junit.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JUnitSetupTest {

    @Test
    void junitDependencyIsAvailable() {
        assertNotNull(org.junit.jupiter.api.Assertions.class);
    }
}
