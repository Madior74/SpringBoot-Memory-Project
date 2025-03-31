package com.example.demo.config;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class MultiDateDeserializer extends StdDeserializer<LocalDate> {
    private static final DateTimeFormatter[] FORMATTERS = {
        DateTimeFormatter.ofPattern("yyyy-MM-dd"),
        DateTimeFormatter.ISO_DATE_TIME
    };

    public MultiDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateStr = p.getText();
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                if (formatter == DateTimeFormatter.ISO_DATE_TIME) {
                    return LocalDateTime.parse(dateStr, formatter).toLocalDate();
                }
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                // continue to next formatter
            }
        }
        throw new DateTimeParseException("Could not parse date", dateStr, 0);
    }
}