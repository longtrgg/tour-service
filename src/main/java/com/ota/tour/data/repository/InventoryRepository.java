package com.ota.tour.data.repository;

import com.ota.tour.data.document.InventoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends MongoRepository<InventoryDocument, Long> {
//    List<ScheduleDocument> getAllByActivityId(Long activityId);
}
