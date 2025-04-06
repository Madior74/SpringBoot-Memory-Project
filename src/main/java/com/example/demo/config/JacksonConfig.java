package com.example.demo.config;

import com.fasterxml.jackson.core.StreamWriteConstraints;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // Augmenter la limite de profondeur d'imbrication
        mapper.getFactory().setStreamWriteConstraints(
            StreamWriteConstraints.builder()
                .maxNestingDepth(2000) // Valeur adaptée à vos besoins
                .build()
        );

        // Enregistrer le module JavaTimeModule pour gérer les types java.time.*
        mapper.registerModule(new JavaTimeModule());

        // Désactiver l'écriture des dates sous forme de timestamps
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // Ajouter un désérialiseur personnalisé pour LocalDateTime
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));

        return mapper;
    }
}