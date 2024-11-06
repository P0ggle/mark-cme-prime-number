package org.example.validation;

public class NumericValidation implements Validation {
    @Override
    public boolean isValid(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}