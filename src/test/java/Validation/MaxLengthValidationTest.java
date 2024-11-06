package Validation;

import org.example.validation.MaxLengthValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaxLengthValidationTest {
    @Test
    void testIsValid() {
        MaxLengthValidation validation = new MaxLengthValidation();
        assertTrue(validation.isValid("123456789"), "9-digit number should be valid");
        assertFalse(validation.isValid("12345678910"), "10-digit number should be invalid for int limit");
    }
}
