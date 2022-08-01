package com.ota.tour.data.migration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ota.tour.data.document.DestinationDocument;
import com.ota.tour.service.DestinationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class DestinationMigration {

    private final DestinationService destinationService;

    public void migrateVN() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<DestinationDocument>> typeReference = new TypeReference<List<DestinationDocument>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/data/destination.json");
        try {
            List<DestinationDocument> destinationDocuments = mapper.readValue(inputStream, typeReference);
            for (DestinationDocument destinationDocument : destinationDocuments) {
                destinationService.saveOrUpdate(destinationDocument);
            }
        } catch (IOException error) {
            log.error("Unable to save destination");
        }
    }
}
