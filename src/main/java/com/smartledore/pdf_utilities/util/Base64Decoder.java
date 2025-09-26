package com.smartledore.pdf_utilities.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Decoder {

    public static boolean isBase64(String input) throws IllegalArgumentException {
        if(input == null) {
            throw new IllegalArgumentException("Input must not be null");
        }

        try {
            Base64.getDecoder().decode(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static String decodeBase64(String input) {
        try {
            byte[] stringContent = Base64.getDecoder().decode(input);
            return new String(stringContent, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            return input;
        }
    }
}
