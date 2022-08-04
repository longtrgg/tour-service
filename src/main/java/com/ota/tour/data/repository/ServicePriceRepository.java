package com.ota.tour.data.repository;

import com.ota.tour.data.document.ServicePriceDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicePriceRepository extends MongoRepository<ServicePriceDocument, Long> {
}
