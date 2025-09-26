package com.smartledore.pdf_utilities.controller;

import com.smartledore.pdf_utilities.model.PdfRequest;
import com.smartledore.pdf_utilities.model.PdfResponse;
import com.smartledore.pdf_utilities.service.IPdfService;
import com.smartledore.pdf_utilities.util.Base64Decoder;
import com.smartledore.pdf_utilities.util.Base64Encoder;
import org.apache.fop.apps.FOPException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/pdf")
public class PdfController {

    private final IPdfService pdfService;
    private final Logger logger;

    public PdfController(IPdfService pdfService) {
        this.pdfService = pdfService;
        this.logger = Logger.getLogger(PdfController.class.getName());
    }

    @PostMapping("/from-xml/{mode}")
    public ResponseEntity<PdfResponse> fromXml(@RequestBody PdfRequest request, @PathVariable String mode) {
        String input = this.getBase64RequestData(request.getInput());

        String stylesheet = request.getStylesheet().isPresent() ?
                getBase64RequestData(request.getStylesheet().get()) :
                null;

        PdfMode pdfMode = PdfMode.getMode(mode).orElse(PdfMode.PDF);

        try {
            String pdfResult = this.pdfService.fromXml(input, stylesheet, pdfMode);
            if(!Base64Decoder.isBase64(pdfResult)) {
                pdfResult = Base64Encoder.encodeBase64(pdfResult);
            }

            return ResponseEntity.ok(new PdfResponse("PDF file created successfully", pdfResult));

        } catch (FOPException | IOException | TransformerException e) {
            this.logger.severe(e.getMessage());

            return ResponseEntity.internalServerError().body(
                    new PdfResponse("PDF file creation failed", e.getMessage())
            );
        }
    }

    @PostMapping("/from-xslfo/{mode}")
    public ResponseEntity<PdfResponse> fromXslFo(@RequestBody PdfRequest request, @PathVariable String mode) {
        String input = this.getBase64RequestData(request.getInput());
        PdfMode pdfMode = PdfMode.getMode(mode).orElse(PdfMode.PDF);

        try {
            String pdfResult = this.pdfService.fromXslFo(input, pdfMode);
            if(!Base64Decoder.isBase64(pdfResult)) {
                pdfResult = Base64Encoder.encodeBase64(pdfResult);
            }

            return ResponseEntity.ok(new PdfResponse("PDF file created successfully", pdfResult));

        } catch (FOPException | IOException | TransformerException e) {
            this.logger.severe(e.getMessage());

            return ResponseEntity.internalServerError().body(
                    new PdfResponse("PDF file creation failed", e.getMessage())
            );
        }
    }

    private String getBase64RequestData(String input) throws IllegalArgumentException {
        return !(Base64Decoder.isBase64(input)) ? Base64Decoder.decodeBase64(input) : input;
    }
}
