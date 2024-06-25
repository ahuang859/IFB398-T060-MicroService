package com.xmlprocessor.demo.controller;

import com.xmlprocessor.demo.service.XmlProcessingService;
import com.xmlprocessor.demo.model.XmlData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/xml")
public class XmlController {

    @Autowired
    private XmlProcessingService xmlProcessingService;

    @PostMapping(consumes = "application/xml", produces = "application/xml")
    public ResponseEntity<XmlData> processXml(@RequestBody String xml) {
        try {
            XmlData data = xmlProcessingService.processXml(xml, XmlData.class);
            // Process the data as needed
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
