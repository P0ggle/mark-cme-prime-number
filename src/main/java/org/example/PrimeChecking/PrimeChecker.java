package org.example.PrimeChecking;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class PrimeChecker {
    private static final Logger logger = Logger.getLogger(PrimeChecker.class.getName());
    private final CacheManager cacheManager;

    public PrimeChecker(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    private Set<String> generateSubSequences(String input) {
        Set<String> subSequences = new HashSet<>();

        for (int start = 0; start < input.length(); start++) {
            for (int end = start + 1; end <= input.length(); end++) {
                subSequences.add(input.substring(start, end));
            }
        }

        logger.info("Generated sub-sequences from input: " + input + " -> " + subSequences);
        return subSequences;
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    public Set<Integer> getPrimeSubsequences(String input) {
        Set<Integer> primes = new HashSet<>();
        Set<String> subSequences = generateSubSequences(input);

        for (String subSequence : subSequences) {
            int number = Integer.parseInt(subSequence);

            if (cacheManager.isPrimeCached(number)) {
                primes.add(number);
                logger.info("Found in cache: " + number);
            } else {
                if (isPrime(number)) {
                    primes.add(number);
                    cacheManager.savePrimes(Set.of(number)); // Save new prime to cache
                    logger.info("Prime number found and added to cache: " + number);
                }
            }
        }

        logger.info("All prime numbers from input " + input + ": " + primes);
        return primes;
    }
}
