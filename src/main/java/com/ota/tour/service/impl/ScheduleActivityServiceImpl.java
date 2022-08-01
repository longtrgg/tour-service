package com.ota.tour.service.impl;

import com.ota.tour.data.document.ScheduleActivityDocument;
import com.ota.tour.data.repository.ScheduleActivityRepository;
import com.ota.tour.service.ScheduleActivityService;
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
public class ScheduleActivityServiceImpl implements ScheduleActivityService {
    private final ScheduleActivityRepository scheduleActivityRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public ScheduleActivityDocument saveOrUpdate(ScheduleActivityDocument scheduleActivityDocument) {
        Assert.notNull(scheduleActivityDocument, "tourScheduleActivity must not be null");
        ZonedDateTime now = ZonedDateTime.now();
        if (scheduleActivityDocument.getId() != null) {
            Optional<ScheduleActivityDocument> updatedOpt = scheduleActivityRepository.findById(scheduleActivityDocument.getId());
            if (updatedOpt.isPresent()) {
                ScheduleActivityDocument updated = updatedOpt.get();
                updated.setScheduleId(scheduleActivityDocument.getScheduleId());
                if (updated.getName() == null) {
                    updated.setName(new ArrayList<>());
                }
                Helper.updateTranslation(scheduleActivityDocument.getName(), updated.getName());
                if (updated.getDescription() == null) {
                    updated.setDescription(new ArrayList<>());
                }
                Helper.updateTranslation(scheduleActivityDocument.getDescription(), updated.getDescription());
                if (updated.getNote() == null) {
                    updated.setNote(new ArrayList<>());
                }
                Helper.updateTranslation(scheduleActivityDocument.getNote(), updated.getNote());
                updated.setStartAt(scheduleActivityDocument.getStartAt());
                updated.setLastModifiedDate(now);
                updated = scheduleActivityRepository.save(updated);

                return updated;
            }
        }
        scheduleActivityDocument.setLastModifiedDate(now);
        scheduleActivityDocument.setCreatedDate(now);
        scheduleActivityDocument.setLastModifiedBy(scheduleActivityDocument.getLastModifiedBy());
        scheduleActivityDocument = scheduleActivityRepository.save(scheduleActivityDocument);
        return scheduleActivityDocument;
    }

    @Override
    public List<ScheduleActivityDocument> getScheduleActivityByScheduleId(Long scheduleId) {
        Assert.notNull(scheduleId, "scheduleId must not be null");
        return scheduleActivityRepository.findAllByScheduleId(scheduleId);
    }

    @Override
    public void deleteScheduleActivity(Long scheduleActivityId) {
        Assert.notNull(scheduleActivityId, "scheduleActivityId must not be null");
        Optional<ScheduleActivityDocument> optionalScheduleActivityDocument = scheduleActivityRepository.findById(scheduleActivityId);
        if (optionalScheduleActivityDocument.isPresent()) {
            ScheduleActivityDocument scheduleActivityDocument = optionalScheduleActivityDocument.get();
            scheduleActivityDocument.setIsDelete(true);
            scheduleActivityRepository.save(scheduleActivityDocument);
        }
    }
}
