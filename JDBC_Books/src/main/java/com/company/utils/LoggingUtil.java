package com.company.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingUtil {

    private static final Logger logger = Logger.getLogger("JPALogger");

    static {
        try {
            FileHandler fileHandler = new FileHandler("jpa_logs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String message) {
        logger.info(message);
        System.out.println(message);
    }

    public static void log(String message, Throwable throwable) {
        logger.severe(message);
        throwable.printStackTrace();
    }
}
