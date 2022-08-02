package com.ota.tour.service.impl;

import com.ota.tour.data.document.ScheduleDocument;
import com.ota.tour.data.repository.ScheduleRepository;
import com.ota.tour.service.ScheduleService;
import com.ota.tour.util.Helper;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public ScheduleDocument saveOrUpdate(ScheduleDocument scheduleDocument) {
        Assert.notNull(scheduleDocument, "tourSchedule must not be null");
        ZonedDateTime now = ZonedDateTime.now();
        if (scheduleDocument.getId() != null) {
            Optional<ScheduleDocument> updatedOpt = scheduleRepository.findById(scheduleDocument.getId());
            if (updatedOpt.isPresent()) {
                ScheduleDocument updated = updatedOpt.get();
                updated.setActivityId(scheduleDocument.getActivityId());
                if (updated.getName() == null) {
                    updated.setName(new ArrayList<>());
                }
                Helper.updateTranslation(scheduleDocument.getName(), updated.getName());
                if (updated.getDescription() == null) {
                    updated.setDescription(new ArrayList<>());
                }
                Helper.updateTranslation(scheduleDocument.getDescription(), updated.getDescription());
                updated.setLastModifiedDate(now);
                updated = scheduleRepository.save(updated);

                return updated;
            }
        }
        scheduleDocument.setLastModifiedDate(now);
        scheduleDocument.setCreatedDate(now);
        scheduleDocument.setLastModifiedBy(scheduleDocument.getLastModifiedBy());
        scheduleDocument = scheduleRepository.save(scheduleDocument);
        return scheduleDocument;
    }

    @Override
    public List<ScheduleDocument> getScheduleByActivityId(Long activityId) {
        Assert.notNull(activityId, "activityId must not be null");
        return scheduleRepository.getAllByActivityId(activityId);
    }

    @Override
    public void deleteSchedule(Long scheduleId) {
        Assert.notNull(scheduleId, "scheduleId must not be null");
        Optional<ScheduleDocument> optionalScheduleDocument = scheduleRepository.findById(scheduleId);
        if (optionalScheduleDocument.isPresent()) {
            ScheduleDocument scheduleDocument = optionalScheduleDocument.get();
            scheduleDocument.setIsDelete(true);
            scheduleRepository.save(scheduleDocument);
        }
    }
}
