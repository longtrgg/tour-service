package com.ota.tour.data.repository;

import com.ota.tour.data.document.ScheduleDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends MongoRepository<ScheduleDocument, Long> {
    List<ScheduleDocument> getAllByActivityId(Long activityId);
}
