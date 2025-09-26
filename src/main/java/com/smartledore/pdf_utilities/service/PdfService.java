package com.smartledore.pdf_utilities.service;

import com.smartledore.pdf_utilities.controller.PdfMode;
import com.smartledore.pdf_utilities.util.Base64Decoder;
import com.smartledore.pdf_utilities.util.Base64Encoder;
import org.apache.fop.apps.*;
import org.apache.fop.apps.io.InternalResourceResolver;
import org.apache.fop.apps.io.ResourceResolverFactory;
import org.apache.fop.configuration.Configuration;
import org.apache.fop.configuration.ConfigurationException;
import org.apache.fop.configuration.DefaultConfigurationBuilder;
import org.springframework.stereotype.Service;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class PdfService implements IPdfService {

    private Logger logger;
    private FopFactory fopFactory;

    @Override
    public String fromXml(String input, String stylesheet, PdfMode pdfModus) throws FOPException, IOException, TransformerException {
        this.setUp();

        input = this.validateBase64Input(input);
        stylesheet = this.validateBase64Input(stylesheet);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            FOUserAgent userAgent = this.getFOUserAgent(pdfModus);

            /* XML wird mit Stylesheet transformiert */
            Transformer transformer = this.getTransformer(Base64Decoder.decodeBase64(stylesheet));

            Fop fop = this.fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, out);

            transformer.transform(
                    new StreamSource(new ByteArrayInputStream(Base64Decoder.decodeBase64(input).getBytes(StandardCharsets.UTF_8))),
                    new SAXResult(fop.getDefaultHandler()));

            return this.getPdfStringFromByteArrayOutputStream(out);
        } catch (FOPException | TransformerException e) {
            this.logger.severe("Fehler bei der FOP-Erzeugung: " + e.getLocalizedMessage());
            throw e;
        }
    }

    @Override
    public String fromXslFo(String input, PdfMode pdfModus) throws IOException, FOPException, TransformerException {this.setUp();

        input = this.validateBase64Input(input);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            FOUserAgent userAgent = this.getFOUserAgent(pdfModus);

            /* identity transformer, da keine Transformation stattfinden muss */
            Transformer transformer = this.getTransformer(null);

            Fop fop = this.fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, out);

            transformer.transform(
                    new StreamSource(new ByteArrayInputStream(Base64Decoder.decodeBase64(input).getBytes(StandardCharsets.UTF_8))),
                    new SAXResult(fop.getDefaultHandler()));

            return this.getPdfStringFromByteArrayOutputStream(out);
        } catch (FOPException | TransformerException e) {
            this.logger.severe("Fehler bei der FOP-Erzeugung: " + e.getLocalizedMessage());
            throw e;
        }
    }

    private String getPdfStringFromByteArrayOutputStream(ByteArrayOutputStream out) {
        byte[] pdf = out.toByteArray();
        return Base64Encoder.encodeBase64(pdf);
    }

    private void setUp() {
        this.logger = Logger.getLogger(PdfService.class.getName());

        try(InputStream configInputStream = this.getClass().getClassLoader().getResourceAsStream("fop.xconf");) {
            DefaultConfigurationBuilder configBuilder = new DefaultConfigurationBuilder();
            Configuration config = configBuilder.build(configInputStream);

            FontClasspathResolver resolver = new FontClasspathResolver(this.getClass().getClassLoader());
            FopFactoryBuilder builder = new FopFactoryBuilder(new File(".").toURI(), resolver).setConfiguration(config);

            InternalResourceResolver internalResourceResolver = builder.getFontManager().getResourceResolver();
            builder.getFontManager().setResourceResolver(
                    ResourceResolverFactory.createInternalResourceResolver(
                            internalResourceResolver.getBaseURI(),
                            resolver
                    )
            );

            this.fopFactory = builder.build();
        } catch(NullPointerException | ConfigurationException | IOException e) {
            this.logger.severe("Fehler beim Laden der FOP-Konfiguration!");
            this.logger.severe(e.getLocalizedMessage());
        }
    }

    private String validateBase64Input(String input) {
        if(input == null || input.isEmpty()) {
            this.logger.severe("Input darf nicht leer sein");
            throw new IllegalArgumentException("Input darf nicht leer sein");
        }

        if(input.startsWith("<?xml")) {
            input = Base64Encoder.encodeBase64(input);
        } else if(!Base64Decoder.isBase64(input)) {
            this.logger.severe("Input nicht im Base64-Format");
            throw new IllegalArgumentException("Input nicht im Base64-Format");
        }

        return input;
    }

    private FOUserAgent getFOUserAgent(PdfMode pdfMode) {
        FOUserAgent userAgent = this.fopFactory.newFOUserAgent();

        if(pdfMode != null && pdfMode != PdfMode.PDF)
        {
            userAgent.setAccessibility(true);

            @SuppressWarnings("unchecked")
            Map<String, String> rendererOptions = userAgent.getRendererOptions();
            rendererOptions.put("pdf-ua-mode", "PDF/UA-1");

            if(pdfMode != PdfMode.PDF_UA && PdfMode.getModeString(pdfMode).isPresent()) {
                rendererOptions.put("pdf-a", "true");
                rendererOptions.put("pdf-a-mode", PdfMode.getModeString(pdfMode).get());
            }
        }

        return userAgent;
    }

    private Transformer getTransformer(String stylesheet) throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        if(null != stylesheet) {
            return transformerFactory.newTransformer(new StreamSource(new StringReader(stylesheet)));
        } else {
            return transformerFactory.newTransformer();
        }
    }
}
