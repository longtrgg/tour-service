package com.ota.tour.data.repository;

import com.ota.tour.data.document.ActivityDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends MongoRepository<ActivityDocument, Long> {
}
