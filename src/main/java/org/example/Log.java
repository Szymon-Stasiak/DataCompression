package org.example;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    @Getter
    private static final Logger logger = LogManager.getLogger("GlobalLogger");

    public static void info(String message) {
        logger.info(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void verbose(String message) {
        logger.trace(message);
    }

    public static void error(String message, Throwable cause) {
        logger.error(message, cause);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }
}
