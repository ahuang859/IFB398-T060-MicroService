package com.example.xmlprocessor.strategy;

import org.springframework.plugin.core.Plugin;
import org.springframework.stereotype.Component;

@Component
public class UppercaseXmlProcessingStrategy implements XmlProcessingStrategy, Plugin<String> {
    @Override
    public String process(String xmlInput) {
        return xmlInput.toUpperCase();
    }
    @Override
    public boolean supports(String delimiter) {
        return "uppercase".equalsIgnoreCase(delimiter);
    }
}

