package com.datasuite.yaml.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/convert")
public class ConversionController {

    private static final Logger logger = LoggerFactory.getLogger(ConversionController.class);

    private final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

    @PostMapping(value = "/yaml_to_xml",consumes = MediaType.APPLICATION_YAML_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> convertYamlToXml(@RequestBody String yamlInput)  {
        try {
            // Create XML mappers
            XmlMapper xmlMapper = new XmlMapper();

            // Parse YAML into a JSON tree
            JsonNode tree = yamlMapper.readTree(yamlInput);

            // Convert JSON tree to XML string
            String xmlOutput = xmlMapper.writeValueAsString(tree);

            return ResponseEntity.ok(xmlOutput);

        }catch (JsonProcessingException e){
            logger.error("Error processing YAML to XML", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to convert YAML to XML : "+e.getMessage());
        }
    }

    @PostMapping(value = "/yaml_to_json",consumes = MediaType.APPLICATION_YAML_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> convertYamlToJson(@RequestBody String yamlInput)  {
        try {
            // Create JSON mappers
            ObjectMapper jsonMapper = new ObjectMapper();

            // Parse YAML into JSON tree
            JsonNode tree = yamlMapper.readTree(yamlInput);

            // Convert tree into pretty-printed JSON string
            String jsonOutput = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree);

            return ResponseEntity.ok(jsonOutput);

        }catch (JsonProcessingException e){
            logger.error("Error processing YAML to XML", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to convert YAML to XML : "+e.getMessage());
        }
    }

}
