package com.example.xmlprocessor.controller;

import com.example.xmlprocessor.strategy.XmlProcessingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Optional;

@RestController
@RequestMapping("/xml")
public class XmlProcessorController {

    private final PluginRegistry<XmlProcessingStrategy, String> pluginRegistry;

    @Autowired
    public XmlProcessorController(PluginRegistry<XmlProcessingStrategy, String> pluginRegistry) {
        this.pluginRegistry = pluginRegistry;
    }

    @PostMapping("/process")
    public String processXml(@RequestBody String xmlInput, @RequestParam String strategyType) {
        loadExternalStrategies();

        Optional<XmlProcessingStrategy> strategyOpt = pluginRegistry.getPluginFor(strategyType);
        if (!strategyOpt.isPresent()) {
            throw new IllegalArgumentException("Invalid strategy type");
        }

        return strategyOpt.get().process(xmlInput);
    }

    private void loadExternalStrategies() {
        try {
            File strategiesDir = new File("strategies");
            if (strategiesDir.exists() && strategiesDir.isDirectory()) {
                URL[] urls = {strategiesDir.toURI().toURL()};
                URLClassLoader loader = new URLClassLoader(urls, this.getClass().getClassLoader());

                for (File file : strategiesDir.listFiles((dir, name) -> name.endsWith(".class"))) {
                    String className = file.getName().replace(".class", "");
                    Class<?> clazz = loader.loadClass(className);

                    if (XmlProcessingStrategy.class.isAssignableFrom(clazz)) {
                        XmlProcessingStrategy strategy = (XmlProcessingStrategy) clazz.getDeclaredConstructor().newInstance();
                        pluginRegistry.getPlugins().add(strategy);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/*
package com.example.xmlprocessor.controller;

import com.example.xmlprocessor.strategy.XmlProcessingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xml")
public class XmlProcessorController {

    private final PluginRegistry<XmlProcessingStrategy, String> pluginRegistry;

    @Autowired
    public XmlProcessorController(PluginRegistry<XmlProcessingStrategy, String> pluginRegistry) {
        this.pluginRegistry = pluginRegistry;
    }

    @PostMapping("/process")
    public String processXml(@RequestBody String xmlInput, @RequestParam String strategyType) {
        XmlProcessingStrategy strategy = pluginRegistry.getPluginFor(strategyType).orElseThrow(
                () -> new IllegalArgumentException("Invalid strategy type")
        );
        return strategy.process(xmlInput);
    }
}
*/
