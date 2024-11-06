package org.example.PrimeChecking;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CacheManager {
    private static final Logger logger = Logger.getLogger(CacheManager.class.getName());
    private final Set<Integer> cache = new HashSet<>();
    private final String filePath;

    public CacheManager(String filePath) {
        this.filePath = filePath;
        ensureFileExists();
        loadCacheFromFile();
        logger.info("Current cache: " + cache);
    }

    private void ensureFileExists() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                logger.info("Created new cache file: " + filePath);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating cache file: " + filePath, e);
        }
    }

    private void loadCacheFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    cache.add(Integer.parseInt(line));
                }
            }
            logger.info("Cache loaded successfully from file: " + filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading cache from file: " + filePath, e);
        }
    }

    public void savePrimes(Set<Integer> primes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Integer prime : primes) {
                if (cache.add(prime)) {
                    writer.write(prime + "\n");
                    logger.info("Prime saved to file and cache: " + prime);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving primes to file: " + filePath, e);
        }
    }

    public boolean isPrimeCached(int number) {
        return cache.contains(number);
    }
}
