package com.ota.tour.service;

import com.ota.tour.data.document.ScheduleDocument;

import java.util.List;

public interface ScheduleService {

    ScheduleDocument saveOrUpdate(ScheduleDocument scheduleDocument);

    List<ScheduleDocument> getScheduleByActivityId(Long activityId);

    void deleteSchedule(Long scheduleId);

}
