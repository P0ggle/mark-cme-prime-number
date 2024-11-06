package PrimeChecking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.Set;

import org.example.PrimeChecking.CacheManager;

import static org.junit.jupiter.api.Assertions.*;

class CacheManagerTest {
    private CacheManager cacheManager;
    private File tempFile;

    @BeforeEach
    void setUp() throws Exception {
        tempFile = Files.createTempFile("prime_cache", ".txt").toFile();
        cacheManager = new CacheManager(tempFile.getAbsolutePath());
    }

    @AfterEach
    void tearDown() {
        tempFile.delete();
    }

    @Test
    void testIsPrimeCached() {
        cacheManager.savePrimes(Set.of(3, 5, 7));
        assertTrue(cacheManager.isPrimeCached(3), "3 should be in the cache");
        assertFalse(cacheManager.isPrimeCached(11), "11 should not be in the cache");
    }

    @Test
    void testSavePrimes() throws Exception {
        cacheManager.savePrimes(Set.of(11, 13));
        assertTrue(cacheManager.isPrimeCached(11), "11 should be in the cache after saving");
        assertTrue(cacheManager.isPrimeCached(13), "13 should be in the cache after saving");
    }

    @Test
    void testSaveEmptySet() {
        cacheManager.savePrimes(Set.of());
        assertFalse(cacheManager.isPrimeCached(2), "Cache should be empty, so 2 should not be found in the cache");
    }
}
