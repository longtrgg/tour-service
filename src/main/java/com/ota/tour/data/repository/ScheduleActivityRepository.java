package com.ota.tour.data.repository;

import com.ota.tour.data.document.ScheduleActivityDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleActivityRepository extends MongoRepository<ScheduleActivityDocument, Long> {
    List<ScheduleActivityDocument> findAllByScheduleId(Long scheduleId);
}
