package com.ota.tour.data.repository;

import com.ota.tour.data.document.ServiceDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends MongoRepository<ServiceDocument, Long> {
    List<ServiceDocument> getAllByActivityId(Long activityId);
}
