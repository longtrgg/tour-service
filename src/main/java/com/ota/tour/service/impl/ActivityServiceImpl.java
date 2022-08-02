package com.ota.tour.service.impl;

import com.ota.tour.data.document.ActivityDocument;
import com.ota.tour.data.repository.ActivityRepository;
import com.ota.tour.service.ActivityService;
import com.ota.tour.util.Helper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.LongSupplier;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public ActivityDocument saveOrUpdate(ActivityDocument activityDocument) {
        Assert.notNull(activityDocument, "tourActivity must not be null");
        ZonedDateTime now = ZonedDateTime.now();
        if (activityDocument.getId() != null) {
            Optional<ActivityDocument> updatedOpt = activityRepository.findById(activityDocument.getId());
            if (updatedOpt.isPresent()) {
                ActivityDocument updated = updatedOpt.get();
                if (updated.getName() == null) {
                    updated.setName(new ArrayList<>());
                }
                Helper.updateTranslation(activityDocument.getName(), updated.getName());
                if (activityDocument.getMeetUpLocation() != null) {
                    updated.setMeetUpLocation(activityDocument.getMeetUpLocation());
                }

                if (updated.getMeetUpAddress() == null) {
                    updated.setMeetUpAddress(new ArrayList<>());
                }
                Helper.updateTranslation(activityDocument.getMeetUpAddress(), updated.getMeetUpAddress());

                if (updated.getOverview() == null) {
                    updated.setOverview(new ArrayList<>());
                }
                Helper.updateTranslation(activityDocument.getOverview(), updated.getOverview());

                if (updated.getPolicy() == null) {
                    updated.setPolicy(new ArrayList<>());
                }
                Helper.updateTranslation(activityDocument.getPolicy(), updated.getPolicy());

                if (updated.getHowToUse() == null) {
                    updated.setHowToUse(new ArrayList<>());
                }
                Helper.updateTranslation(activityDocument.getHowToUse(), updated.getHowToUse());

                if (updated.getImportantInformation() == null) {
                    updated.setImportantInformation(new ArrayList<>());
                }
                Helper.updateTranslation(activityDocument.getImportantInformation(), updated.getImportantInformation());

                if (updated.getMeetUpInformation() == null) {
                    updated.setMeetUpInformation(new ArrayList<>());
                }
                Helper.updateTranslation(activityDocument.getMeetUpInformation(), updated.getMeetUpInformation());
                if (updated.getNote() == null) {
                    updated.setNote(new ArrayList<>());
                }
                Helper.updateTranslation(activityDocument.getNote(), updated.getNote());
                if (activityDocument.getDestinationIds() != null) {
                    updated.setDestinationIds(activityDocument.getDestinationIds());
                }
                updated.setStatus(activityDocument.getStatus());
                updated.setLastModifiedDate(now);
                updated = activityRepository.save(updated);

                return updated;
            }
        }
        activityDocument.setLastModifiedDate(now);
        activityDocument.setCreatedDate(now);
        activityDocument.setLastModifiedBy(activityDocument.getLastModifiedBy());
        activityDocument = activityRepository.save(activityDocument);
        return activityDocument;
    }

    @Override
    public Page<ActivityDocument> getActivity(Long activityId, Pageable pageable) {
        Query query = new Query().with(pageable);
        if (activityId != null && activityId != 0) {
            query.addCriteria(Criteria.where("id").is(activityId));
        }
        List<ActivityDocument> list = mongoTemplate.find(query, ActivityDocument.class);
        LongSupplier total = () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), ActivityDocument.class);
        //        if (total.getAsLong() > pageable.getPageSize()) {
//            total = () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), ActivityDocument.class);
//        }
        return PageableExecutionUtils.getPage(list, pageable, total);
    }

    @Override
    public void deleteActivity(Long activityId) {
        Assert.notNull(activityId, "activityId must not be null");
        Optional<ActivityDocument> optionalActivityDocument = activityRepository.findById(activityId);
        if (optionalActivityDocument.isPresent()) {
            ActivityDocument activityDocument = optionalActivityDocument.get();
            activityDocument.setIsDelete(true);
            activityRepository.save(activityDocument);
        }
    }
}
