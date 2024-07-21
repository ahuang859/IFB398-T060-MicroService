package com.example.xmlprocessor.strategy;

public class UppercaseXmlProcessingStrategy implements XmlProcessingStrategy {
    @Override
    public String process(String xmlInput) {
        return xmlInput.toUpperCase();
    }
}
