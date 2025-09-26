package com.smartledore.pdf_utilities.controller;

import lombok.Getter;

import java.util.Optional;

public enum PdfMode {
    PDF("pdf"),
    PDF_UA("pdf-ua-1"),
    PDF_A1_A("pdf-a-1a"),
    PDF_A1_B("pdf-a-1b"),
    PDF_A2_A("pdf-a-2a"),
    PDF_A2_B("pdf-a-2b"),
    PDF_A2_U("pdf-a-2u"),
    PDF_A3_A("pdf-a-3a"),
    PDF_A3_B("pdf-a-3b"),
    PDF_A3_U("pdf-a-3u");

    @Getter
    private final String mode;

    PdfMode(String mode) {
        this.mode = mode;
    }

    public static Optional<PdfMode> getMode(String mode) {
        for(PdfMode pdfModus : PdfMode.values()) {
            if(pdfModus.getMode().equals(mode)) {
                return Optional.of(pdfModus);
            }
        }

        return Optional.empty();
    }

    public static Optional<String> getModeString(PdfMode pdfMode) {
        String result;

        switch(pdfMode) {
            case PDF_UA:
                result = "PDF/UA-1";
                break;
            case PDF_A1_A:
                result = "PDF/A-1a";
                break;
            case PDF_A1_B:
                result = "PDF/A-1b";
                break;
            case PDF_A2_A:
                result = "PDF/A2-a";
                break;
            case PDF_A2_B:
                result = "PDF/A2-b";
                break;
            case PDF_A2_U:
                result = "PDF/A2-u";
                break;
            case PDF_A3_A:
                result = "PDF/A3-a";
                break;
            case PDF_A3_B:
                result = "PDF/A3-b";
                break;
            case PDF_A3_U:
                result = "PDF/A3-u";
                break;
            default:
                return Optional.empty();
        }

        return Optional.of(result);
    }
}
