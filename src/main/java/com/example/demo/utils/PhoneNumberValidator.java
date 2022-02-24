package com.example.demo.utils;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class PhoneNumberValidator implements Predicate<String> {

    private static final Predicate<String> IS_NUMBER_VALID =
            Pattern.compile(
                    "^(?:\\+88|88)?(01[3-9]\\d{8})$",
                    Pattern.CASE_INSENSITIVE
            ).asPredicate();
    @Override
    public boolean test(String number) {
        return number.startsWith("01")&&
                number.length() == 11;
    }
}
