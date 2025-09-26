package com.smartledore.pdf_utilities.service;

import com.smartledore.pdf_utilities.controller.PdfMode;
import org.apache.fop.apps.FOPException;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface IPdfService {
    String fromXml(String input, String stylesheet, PdfMode pdfModus) throws FOPException, IOException, TransformerException;
    String fromXslFo(String input, PdfMode pdfModus) throws IOException, FOPException, TransformerException;
}
