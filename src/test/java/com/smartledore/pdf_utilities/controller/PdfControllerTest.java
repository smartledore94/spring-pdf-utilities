package com.smartledore.pdf_utilities.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartledore.pdf_utilities.ResourceConstants;
import com.smartledore.pdf_utilities.model.PdfResponse;
import com.smartledore.pdf_utilities.service.PdfService;
import com.smartledore.pdf_utilities.util.InputStreamHelper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PdfControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getPdfFromXml() throws Exception {
        Class<PdfService> clazz = PdfService.class;

        try(
                InputStream inputStream = clazz.getResourceAsStream(ResourceConstants.XML_FOR_PDF);
                InputStream stylesheetStream = clazz.getResourceAsStream(ResourceConstants.XSL_FOR_PDF)
        ) {
            JSONObject requestBody = this.buildRequestBody(
                    InputStreamHelper.read(inputStream),
                    InputStreamHelper.read(stylesheetStream));

            MvcResult response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/pdf/from-xml/pdf")
                            .content(requestBody.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            PdfResponse documentResponse = this.buildDocumentResponse(response);
            assertEquals("PDF file created successfully", documentResponse.getMessage());
        }
    }

    @Test
    public void getPdfUAFromXml() throws Exception {
        Class<PdfService> clazz = PdfService.class;

        try(
                InputStream inputStream = clazz.getResourceAsStream(ResourceConstants.XML_FOR_PDF);
                InputStream stylesheetStream = clazz.getResourceAsStream(ResourceConstants.XSL_FOR_PDF_UA)
        ) {
            JSONObject requestBody = this.buildRequestBody(
                    InputStreamHelper.read(inputStream),
                    InputStreamHelper.read(stylesheetStream));

            MvcResult response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/pdf/from-xml/pdf-ua-1")
                            .content(requestBody.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            PdfResponse documentResponse = this.buildDocumentResponse(response);
            assertEquals("PDF file created successfully", documentResponse.getMessage());
        }
    }

    @Test
    public void getPdfFromXslFo() throws Exception {
        Class<PdfService> clazz = PdfService.class;

        try(InputStream inputStream = clazz.getResourceAsStream(ResourceConstants.XSL_FO_FOR_PDF)) {
            JSONObject requestBody = this.buildRequestBody(
                    InputStreamHelper.read(inputStream),
                    null);

            MvcResult response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/pdf/from-xslfo/pdf")
                            .content(requestBody.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            PdfResponse documentResponse = this.buildDocumentResponse(response);
            assertEquals("PDF file created successfully", documentResponse.getMessage());
        }
    }

    @Test
    public void getPdfUAFromXslFo() throws Exception {
        Class<PdfService> clazz = PdfService.class;

        try(InputStream inputStream = clazz.getResourceAsStream(ResourceConstants.XSL_FO_FOR_PDF_UA)) {
            JSONObject requestBody = this.buildRequestBody(
                    InputStreamHelper.read(inputStream),
                    null);

            MvcResult response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/pdf/from-xslfo/pdf-ua-1")
                            .content(requestBody.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            PdfResponse documentResponse = this.buildDocumentResponse(response);
            assertEquals("PDF file created successfully", documentResponse.getMessage());
        }
    }

    @Test
    public void getPdfFromBase64Xml() throws Exception {
        Class<PdfService> clazz = PdfService.class;

        try(
                InputStream inputStream = clazz.getResourceAsStream(ResourceConstants.BASE64_XML_FOR_PDF);
                InputStream stylesheetStream = clazz.getResourceAsStream(ResourceConstants.BASE64_XSL_FOR_PDF)
        ) {
            JSONObject requestBody = this.buildRequestBody(
                    InputStreamHelper.read(inputStream),
                    InputStreamHelper.read(stylesheetStream));

            MvcResult response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/pdf/from-xml/pdf")
                            .content(requestBody.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            PdfResponse documentResponse = this.buildDocumentResponse(response);
            assertEquals("PDF file created successfully", documentResponse.getMessage());
        }
    }

    @Test
    public void getPdfUAFromBase64Xml() throws Exception {
        Class<PdfService> clazz = PdfService.class;

        try(
                InputStream inputStream = clazz.getResourceAsStream(ResourceConstants.BASE64_XML_FOR_PDF);
                InputStream stylesheetStream = clazz.getResourceAsStream(ResourceConstants.BASE64_XSL_FOR_PDF_UA)
        ) {
            JSONObject requestBody = this.buildRequestBody(
                    InputStreamHelper.read(inputStream),
                    InputStreamHelper.read(stylesheetStream));

            MvcResult response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/pdf/from-xml/pdf-ua-1")
                            .content(requestBody.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            PdfResponse documentResponse = this.buildDocumentResponse(response);
            assertEquals("PDF file created successfully", documentResponse.getMessage());
        }
    }

    @Test
    public void getPdfFromBase64XslFo() throws Exception {
        Class<PdfService> clazz = PdfService.class;

        try(InputStream inputStream = clazz.getResourceAsStream(ResourceConstants.XSL_FO_BASE64_FOR_PDF)) {
            JSONObject requestBody = this.buildRequestBody(
                    InputStreamHelper.read(inputStream),
                    null);

            MvcResult response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/pdf/from-xslfo/pdf")
                            .content(requestBody.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            PdfResponse documentResponse = this.buildDocumentResponse(response);
            assertEquals("PDF file created successfully", documentResponse.getMessage());
        }
    }

    @Test
    public void getPdfUAFromBase64XslFo() throws Exception {
        Class<PdfService> clazz = PdfService.class;

        try(InputStream inputStream = clazz.getResourceAsStream(ResourceConstants.XSL_FO_BASE64_FOR_PDF_UA)) {
            JSONObject requestBody = this.buildRequestBody(
                    InputStreamHelper.read(inputStream),
                    null);

            MvcResult response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/pdf/from-xslfo/pdf-ua-1")
                            .content(requestBody.toString())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            PdfResponse documentResponse = this.buildDocumentResponse(response);
            assertEquals("PDF file created successfully", documentResponse.getMessage());
        }
    }

    private PdfResponse buildDocumentResponse(MvcResult response) throws UnsupportedEncodingException, JsonProcessingException {
        String responseBody = response.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseBody, PdfResponse.class);
    }

    private JSONObject buildRequestBody(String input, String stylesheet) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("input", input);
        requestMap.put("stylesheet", stylesheet);
        return new JSONObject(requestMap);
    }
}
