package com.example.demo.config;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class EpochMillisToLocalDateTimeDeserializer 
    extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) 
        throws IOException {
        long epochMillis = p.getLongValue();
        return LocalDateTime.ofInstant(
            Instant.ofEpochMilli(epochMillis),
            ZoneId.systemDefault()
        );
    }
}