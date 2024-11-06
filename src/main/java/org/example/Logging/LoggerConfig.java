package org.example.Logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerConfig {

    public static void run() {
        try {
            FileHandler fileHandler = new FileHandler("prime_application.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(java.util.logging.Level.ALL);

            Logger rootLogger = Logger.getLogger("");
            rootLogger.addHandler(fileHandler);

            System.out.println("Logging configured to save to prime_application.log");

        } catch (IOException e) {
            System.err.println("Failed to set up file logging: " + e.getMessage());
        }
    }
}
