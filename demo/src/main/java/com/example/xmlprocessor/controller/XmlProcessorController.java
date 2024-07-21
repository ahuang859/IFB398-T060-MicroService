package com.example.xmlprocessor.controller;

import com.example.xmlprocessor.context.XmlProcessorContext;
import com.example.xmlprocessor.strategy.LowercaseXmlProcessingStrategy;
import com.example.xmlprocessor.strategy.UppercaseXmlProcessingStrategy;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xml")
public class XmlProcessorController {

    private final XmlProcessorContext context = new XmlProcessorContext();

    @PostMapping("/process")
    public String processXml(@RequestBody String xmlInput, @RequestParam String strategyType) {
        switch (strategyType.toLowerCase()) {
            case "lowercase":
                context.setStrategy(new LowercaseXmlProcessingStrategy());
                break;
            case "uppercase":
                context.setStrategy(new UppercaseXmlProcessingStrategy());
                break;
            default:
                throw new IllegalArgumentException("Invalid strategy type");
        }

        return context.executeStrategy(xmlInput);
    }
}
