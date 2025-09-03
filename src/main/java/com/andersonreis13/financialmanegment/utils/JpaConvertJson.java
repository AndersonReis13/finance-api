package com.andersonreis13.financialmanegment.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JpaConvertJson implements AttributeConverter<Map<String, Object>, String>{

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(Map<String, Object> data) {
        if (data == null) return null;
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro convertendo Map para JSON", e);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String s) {
        if (s == null) return null;
        try {
            return objectMapper.readValue(s, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException("Erro convertendo JSON para Map", e);
        }
    }
}
