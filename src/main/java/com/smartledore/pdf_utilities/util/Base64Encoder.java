package com.smartledore.pdf_utilities.util;

import java.util.Base64;

public class Base64Encoder {

    public static String encodeBase64(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public static String encodeBase64(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }
}
