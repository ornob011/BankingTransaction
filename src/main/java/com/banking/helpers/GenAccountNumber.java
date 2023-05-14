package com.banking.helpers;

import java.util.Random;

// This class is used to generate the account number.
public class GenAccountNumber {

    public static int generateAccountNumber() {
        int accountNumber;
        Random random = new Random();
        int bound = 1000;
        accountNumber = bound * random.nextInt(bound);
        return accountNumber;
    }
    // End Of Generate Account Number Method.
}
