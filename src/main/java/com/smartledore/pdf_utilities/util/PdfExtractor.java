package com.smartledore.pdf_utilities.util;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfExtractor {

    public static String extractText(String source) throws IOException {
        String content;
        try(PDDocument doc = Loader.loadPDF(new File(source));) {
            PDFTextStripper stripper = new PDFTextStripper();
            content = stripper.getText(doc);
        }
        return content;
    }
}
