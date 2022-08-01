package com.ota.tour.service;

import com.ota.tour.data.document.ScheduleActivityDocument;

import java.util.List;

public interface ScheduleActivityService {

    ScheduleActivityDocument saveOrUpdate(ScheduleActivityDocument scheduleActivityDocument);

    List<ScheduleActivityDocument> getScheduleActivityByScheduleId(Long scheduleId);

    void deleteScheduleActivity(Long scheduleActivityId);

}
