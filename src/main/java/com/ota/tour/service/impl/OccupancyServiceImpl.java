package com.ota.tour.service.impl;

import com.ota.tour.data.document.OccupancyDocument;
import com.ota.tour.data.repository.OccupancyRepository;
import com.ota.tour.service.OccupancyService;
import com.ota.tour.util.Helper;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OccupancyServiceImpl implements OccupancyService {
    private final OccupancyRepository occupancyRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public OccupancyDocument saveOrUpdate(OccupancyDocument occupancyDocument) {
        Assert.notNull(occupancyDocument, "tourOccupancy must not be null");
        ZonedDateTime now = ZonedDateTime.now();
        List<OccupancyDocument> occupancyDocumentList = occupancyRepository.findByActivityIdAndIsDeleteFalse(occupancyDocument.getActivityId());
        if (occupancyDocument.getId() != null) {
            Optional<OccupancyDocument> updatedOpt = occupancyRepository.findById(occupancyDocument.getId());
            if (updatedOpt.isPresent()) {
                OccupancyDocument updated = updatedOpt.get();
                updated.setActivityId(occupancyDocument.getActivityId());
                if (updated.getName() == null) {
                    updated.setName(new ArrayList<>());
                }
                Helper.updateTranslation(occupancyDocument.getName(), updated.getName());
                if (updated.getDescription() == null) {
                    updated.setDescription(new ArrayList<>());
                }
                Helper.updateTranslation(occupancyDocument.getDescription(), updated.getDescription());
                if (occupancyDocument.getMaxOccupancy() != null) {
                    updated.setMaxOccupancy(occupancyDocument.getMaxOccupancy());
                }
                if (occupancyDocument.getMinOccupancy() != null) {
                    updated.setMinOccupancy(occupancyDocument.getMinOccupancy());
                }
                if (occupancyDocument.getMinAge() != null) {
                    updated.setMinAge(occupancyDocument.getMinAge());
                }
                if (occupancyDocument.getMaxAge() != null) {
                    updated.setMaxAge(occupancyDocument.getMaxAge());
                }
                if (occupancyDocument.getMinLengthCm() != null) {
                    updated.setMinLengthCm(occupancyDocument.getMinLengthCm());
                }
                if (occupancyDocument.getMaxLengthCm() != null) {
                    updated.setMaxLengthCm(occupancyDocument.getMaxLengthCm());
                }
                if (occupancyDocument.getDescriptionType() != null) {
                    updated.setDescriptionType(occupancyDocument.getDescriptionType());
                }
                if (occupancyDocument.getPrimary() != null) {
                    if (occupancyDocument.getPrimary() != null) {
                        if (occupancyDocument.getPrimary() != null && occupancyDocument.getPrimary()) {
                            if (occupancyDocumentList.size() > 1) {
                                for (OccupancyDocument doc : occupancyDocumentList) {
                                    doc.setPrimary(false);
                                    occupancyRepository.save(doc);
                                }
                            }
                        }
                        updated.setPrimary(occupancyDocument.getPrimary());
                    }
                }
                updated.setLastModifiedDate(now);
                updated = occupancyRepository.save(updated);

                return updated;
            }
        }
        if (occupancyDocumentList.stream().filter(occupancy -> occupancy.getPrimary()).collect(Collectors.toList()).size() == 0) {
            occupancyDocument.setPrimary(true);
        }
        occupancyDocument.setLastModifiedDate(now);
        occupancyDocument.setCreatedDate(now);
        occupancyDocument.setLastModifiedBy(occupancyDocument.getLastModifiedBy());
        occupancyDocument = occupancyRepository.save(occupancyDocument);
        return occupancyDocument;
    }

    @Override
    public List<OccupancyDocument> getOccupancyByActivityId(Long activityId) {
        Assert.notNull(activityId, "activityId must not be null");
        return occupancyRepository.getAllByActivityId(activityId);
    }

    @Override
    public void deleteOccupancy(Long occupancyId) {
        Assert.notNull(occupancyId, "occupancyId must not be null");
        Optional<OccupancyDocument> optionalOccupancyDocument = occupancyRepository.findById(occupancyId);
        if (optionalOccupancyDocument.isPresent()) {
            OccupancyDocument occupancyDocument = optionalOccupancyDocument.get();
            occupancyDocument.setIsDelete(true);
            occupancyRepository.save(occupancyDocument);
        }
    }
}
