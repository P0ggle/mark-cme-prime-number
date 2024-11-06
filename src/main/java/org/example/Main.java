package org.example;

import org.example.Logging.LoggerConfig;
import org.example.PrimeChecking.PrimeSequenceProcessor;

public class Main {
    public static void main(String[] args) {
        LoggerConfig.run();
        PrimeSequenceProcessor processor = new PrimeSequenceProcessor();
        processor.process();
    }
}
