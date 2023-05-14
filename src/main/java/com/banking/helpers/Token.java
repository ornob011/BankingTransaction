package com.banking.helpers;

import java.util.UUID;

// This class is used to generate the token.
public class Token {
    public static String generateToken() {
        String token = UUID.randomUUID().toString(); // This is used to generate the token.
        return token;
    }
}
