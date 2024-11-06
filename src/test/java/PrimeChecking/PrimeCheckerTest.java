package PrimeChecking;

import org.example.PrimeChecking.CacheManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.Set;

import org.example.PrimeChecking.PrimeChecker;

import static org.junit.jupiter.api.Assertions.*;

class PrimeCheckerTest {
    private PrimeChecker primeChecker;
    private CacheManager cacheManager;
    private File tempFile;

    @BeforeEach
    void setUp() throws Exception {
        tempFile = Files.createTempFile("prime_cache", ".txt").toFile();
        cacheManager = new CacheManager(tempFile.getAbsolutePath());
        primeChecker = new PrimeChecker(cacheManager);
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testIsPrime() {
        assertFalse(primeChecker.isPrime(1), "1 is not a prime number");
        assertTrue(primeChecker.isPrime(2), "2 is a prime number");
        assertTrue(primeChecker.isPrime(3), "3 is a prime number");
        assertFalse(primeChecker.isPrime(4), "4 is not a prime number");
        assertTrue(primeChecker.isPrime(5), "5 is a prime number");
        assertFalse(primeChecker.isPrime(9), "9 is not a prime number");
    }

    @Test
    void testGetPrimeSubsequences() {
        Set<Integer> primes = primeChecker.getPrimeSubsequences("12345");

        assertEquals(Set.of(2, 3, 5, 23), primes, "Expected prime numbers from sequence 12345 are 2, 3, 5, 23");
    }

    @Test
    void testGetPrimeSubsequencesWithEmptyString() {
        Set<Integer> primes = primeChecker.getPrimeSubsequences("");
        assertTrue(primes.isEmpty(), "Empty string should result in an empty set of primes");
    }

    @Test
    void testGetPrimeSubsequencesWithSingleDigit() {
        Set<Integer> primes = primeChecker.getPrimeSubsequences("4");
        assertTrue(primes.isEmpty(), "Single non-prime digit should result in an empty set of primes");
    }

    @Test
    void testGetPrimeSubsequencesWithMoreThanOneDigit() {
        Set<Integer> primes = primeChecker.getPrimeSubsequences("444444");
        assertTrue(primes.isEmpty(), "Multi non-prime digit should result in an empty set of primes");
    }
}
