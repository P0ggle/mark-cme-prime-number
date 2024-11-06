package org.example.validation;

public class MaxLengthValidation implements Validation {
    private final int maxLength;

    public MaxLengthValidation() {
        this.maxLength = String.valueOf(Integer.MAX_VALUE).length();
    }

    @Override
    public boolean isValid(String input) {
        return input.length() <= maxLength;
    }
}

