package com.ota.tour.data.repository;

import com.ota.tour.data.document.OccupancyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccupancyRepository extends MongoRepository<OccupancyDocument, Long> {
    List<OccupancyDocument> getAllByActivityId(Long activityId);

    List<OccupancyDocument> findByActivityIdAndIsDeleteFalse(Long activityId);
}
