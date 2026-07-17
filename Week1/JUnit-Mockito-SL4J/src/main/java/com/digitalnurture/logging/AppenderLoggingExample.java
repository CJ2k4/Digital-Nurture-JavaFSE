package com.digitalnurture.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppenderLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(AppenderLoggingExample.class);

    public static void main(String[] args) {
        logger.debug("Debug message written to console and file appenders");
        logger.info("Info message written to console and file appenders");
        logger.warn("Warning message written to console and file appenders");
        logger.error("Error message written to console and file appenders");
    }
}
