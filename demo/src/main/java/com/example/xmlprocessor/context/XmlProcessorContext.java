package com.example.xmlprocessor.context;

import com.example.xmlprocessor.strategy.XmlProcessingStrategy;

public class XmlProcessorContext {
    private XmlProcessingStrategy strategy;

    public void setStrategy(XmlProcessingStrategy strategy) {
        this.strategy = strategy;
    }

    public String executeStrategy(String xmlInput) {
        return strategy.process(xmlInput);
    }
}
