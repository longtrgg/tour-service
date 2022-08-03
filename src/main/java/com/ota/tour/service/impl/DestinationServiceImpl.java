package com.ota.tour.service.impl;

import com.ota.tour.data.document.DestinationDocument;
import com.ota.tour.data.repository.DestinationRepository;
import com.ota.tour.service.DestinationService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DestinationServiceImpl implements DestinationService {
    private final DestinationRepository destinationRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public DestinationDocument saveOrUpdate(DestinationDocument destinationDocument) {
        Assert.notNull(destinationDocument, "tourSchedule must not be null");
        ZonedDateTime now = ZonedDateTime.now();
        if (destinationDocument.getId() != null) {
            Optional<DestinationDocument> updatedOpt = destinationRepository.findById(destinationDocument.getId());
            if (updatedOpt.isPresent()) {
                DestinationDocument updated = updatedOpt.get();
                if (destinationDocument.getName() != null) {
                    updated.setName(destinationDocument.getName());
                }
                if (destinationDocument.getSlug() != null) {
                    updated.setSlug(destinationDocument.getSlug());
                }
                if (destinationDocument.getFullName() != null) {
                    updated.setFullName(destinationDocument.getFullName());
                }
                updated.setLastModifiedDate(now);
                updated = destinationRepository.save(updated);

                return updated;
            }
        }
        destinationDocument.setLastModifiedDate(now);
        destinationDocument.setCreatedDate(now);
        destinationDocument.setLastModifiedBy(destinationDocument.getLastModifiedBy());
        destinationDocument = destinationRepository.save(destinationDocument);
        return destinationDocument;
    }

    @Override
    public Optional<DestinationDocument> getDestinationById(Long destinationId) {
        Assert.notNull(destinationId, "activityId must not be null");
        return destinationRepository.findById(destinationId);
    }
}
