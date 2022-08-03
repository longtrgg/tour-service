package com.ota.tour.service;

import com.ota.tour.data.document.OccupancyDocument;

import java.util.List;

public interface OccupancyService {

    OccupancyDocument saveOrUpdate(OccupancyDocument occupancyDocument);

    List<OccupancyDocument> getOccupancyByActivityId(Long activityId);

    void deleteOccupancy(Long occupancyId);

}
