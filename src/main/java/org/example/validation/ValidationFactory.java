package org.example.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationFactory {
    public static List<Validation> getValidators() {
        List<Validation> validation = new ArrayList<>();
        validation.add(new NumericValidation());
        validation.add(new MaxLengthValidation());
        return validation;
    }
}
