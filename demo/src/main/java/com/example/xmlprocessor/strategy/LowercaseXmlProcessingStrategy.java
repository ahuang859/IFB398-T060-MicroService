package com.example.xmlprocessor.strategy;

public class LowercaseXmlProcessingStrategy implements XmlProcessingStrategy {
    @Override
    public String process(String xmlInput) {
        return xmlInput.toLowerCase();
    }
}
