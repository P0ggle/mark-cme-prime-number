package Validation;

import org.junit.jupiter.api.Test;

import org.example.validation.NumericValidation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumericValidationTest {

    @Test
    void testIsValid() {
        NumericValidation validation = new NumericValidation();
        assertTrue(validation.isValid("12345"), "Only numeric characters should be valid");
        assertFalse(validation.isValid("123a5"), "Alphanumeric characters should be invalid");
        assertFalse(validation.isValid(""), "Empty string should be invalid");
    }
}
