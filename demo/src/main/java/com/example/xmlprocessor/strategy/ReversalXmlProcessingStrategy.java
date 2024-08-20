package com.example.xmlprocessor.strategy;

import org.springframework.plugin.core.Plugin;
import org.springframework.stereotype.Component;

@Component
public class ReversalXmlProcessingStrategy implements XmlProcessingStrategy, Plugin<String> {

    @Override
    public String process(String xmlInput) {
        return new StringBuilder(xmlInput).reverse().toString();
    }

    @Override
    public boolean supports(String delimiter) {
        return "reverse".equalsIgnoreCase(delimiter);
    }
}
