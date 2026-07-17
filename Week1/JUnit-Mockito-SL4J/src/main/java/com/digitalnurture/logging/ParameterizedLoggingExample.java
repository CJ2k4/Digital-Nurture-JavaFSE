package com.digitalnurture.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String user = "alice";
        int requestCount = 42;

        logger.info("User {} made {} requests", user, requestCount);
        logger.debug("Processing request for user {} with id {}", user, 1001L);

        if (logger.isDebugEnabled()) {
            logger.debug("Expensive debug details for {}", user);
        }
    }
}
