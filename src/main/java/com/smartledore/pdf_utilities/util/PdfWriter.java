package com.smartledore.pdf_utilities.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class PdfWriter {

    public static void write(String destination, String pdf) throws IOException {
        File file = new File(destination);
        try(FileOutputStream fos = new FileOutputStream(file);) {
            byte[] decoded = Base64.getDecoder().decode(pdf);
            fos.write(decoded);
        }
    }
}
