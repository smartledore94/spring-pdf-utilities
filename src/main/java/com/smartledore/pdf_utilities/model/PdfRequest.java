package com.smartledore.pdf_utilities.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@NoArgsConstructor
public class PdfRequest {

    @Getter
    @Setter
    private String input;

    @Setter
    private String stylesheet;

    public PdfRequest(String input, String stylesheet) {
        this.input = input;

        if(stylesheet != null) {
            this.stylesheet = stylesheet;
        }
    }

    public Optional<String> getStylesheet() {
        return Optional.ofNullable(stylesheet);
    }
}
