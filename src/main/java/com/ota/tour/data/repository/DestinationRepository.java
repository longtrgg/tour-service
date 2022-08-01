package com.ota.tour.data.repository;

import com.ota.tour.data.document.DestinationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends MongoRepository<DestinationDocument, Long> {
//    List<DestinationDocument> getAllByActivityId(Long activityId);
}
