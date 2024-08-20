package com.example.xmlprocessor.strategy;

import org.springframework.plugin.core.Plugin;
import org.springframework.stereotype.Component;

@Component
public class LowercaseXmlProcessingStrategy implements XmlProcessingStrategy, Plugin<String> {
    @Override
    public String process(String xmlInput) {
        return xmlInput.toLowerCase();
    }

    @Override
    public boolean supports(String delimiter) {
        return "lowercase".equalsIgnoreCase(delimiter);
    }
}
