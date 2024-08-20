package com.example.xmlprocessor.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.plugin.core.config.EnablePluginRegistries;
import com.example.xmlprocessor.strategy.XmlProcessingStrategy;

@Configuration
@EnablePluginRegistries(XmlProcessingStrategy.class)
public class PluginConfig {
}
