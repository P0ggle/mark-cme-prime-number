package org.example.PrimeChecking;

import org.example.validation.Validation;
import org.example.validation.ValidationFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PrimeSequenceProcessor {
    private final List<Validation> validation;
    private final PrimeChecker primeChecker;
    private final CacheManager cacheManager;

//  Constructor to be run from Main(), Cache manager has a constructor to populate the file into memory on runtime
    public PrimeSequenceProcessor() {
        this.validation = ValidationFactory.getValidators();
        this.cacheManager = new CacheManager();
        this.primeChecker = new PrimeChecker(this.cacheManager);
    }

    public void process() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a numeric sequence:");
        String input = scanner.nextLine();

//      Validate the input with all validators
        for (Validation validation : validation) {
            if (!validation.isValid(input)) {
                System.out.println("Invalid input! Please enter a numeric sequence within allowed limits.");
                return;
            }
        }

//      Get prime numbers from the sequence
        Set<Integer> allPrimes = primeChecker.getPrimeSubsequences(input);

//      Separate primes into cached and new primes
        Set<Integer> cachedPrimes = new HashSet<>();
        Set<Integer> newPrimes = new HashSet<>();

        for (Integer prime : allPrimes) {
            if (cacheManager.isPrimeCached(prime)) {
                cachedPrimes.add(prime);
            } else {
                newPrimes.add(prime);
            }
        }

//      Save new primes to cache and file
        cacheManager.savePrimes(newPrimes);

//      Display the results
        System.out.println("Original sequence: " + input);

//      Display the results
        System.out.println("Prime numbers in sequence: " + allPrimes);

        if (!cachedPrimes.isEmpty()) {
            System.out.println("Note: The following primes were already cached and written to the file: " + cachedPrimes);
        }

        if (!newPrimes.isEmpty()) {
            System.out.println("Newly found primes (now saved to cache and file): " + newPrimes);
        }
    }
}
