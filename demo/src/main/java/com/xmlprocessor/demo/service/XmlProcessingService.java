package com.xmlprocessor.demo.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

@Service
public class XmlProcessingService {

    private final XmlMapper xmlMapper;

    public XmlProcessingService() {
        this.xmlMapper = new XmlMapper();
    }

    public <T> T processXml(String xml, Class<T> valueType) throws Exception {
        return xmlMapper.readValue(xml, valueType);
    }
}
