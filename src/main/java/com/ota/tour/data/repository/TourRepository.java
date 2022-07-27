package com.ota.tour.data.repository;

import com.ota.tour.data.document.TourDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends MongoRepository<TourDocument, Long> {

//    Page<TourDocument> findBy(Long supplierId, Pageable pageable);

}
