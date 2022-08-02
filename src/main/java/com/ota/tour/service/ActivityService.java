package com.ota.tour.service;

import com.ota.tour.data.document.ActivityDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityService {

    ActivityDocument saveOrUpdate(ActivityDocument activityDocument);

    Page<ActivityDocument> getActivity(Long activityId, Pageable pageable);

    void deleteActivity(Long activityId);

}
