package com.ota.tour.service;

import com.ota.tour.data.document.DestinationDocument;

import java.util.Optional;

public interface DestinationService {

    DestinationDocument saveOrUpdate(DestinationDocument scheduleDocument);

    Optional<DestinationDocument> getDestinationById(Long destinationId);

}
