package com.ota.tour.service.impl;

import com.ota.tour.data.document.ServiceDocument;
import com.ota.tour.data.repository.ServiceRepository;
import com.ota.tour.service.ServiceService;
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
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public ServiceDocument saveOrUpdate(ServiceDocument serviceDocument) {
        Assert.notNull(serviceDocument, "tourServiceDocument must not be null");
        ZonedDateTime now = ZonedDateTime.now();
        if (serviceDocument.getId() != null) {
            Optional<ServiceDocument> updatedOpt = serviceRepository.findById(serviceDocument.getId());
            if (updatedOpt.isPresent()) {
                ServiceDocument updated = updatedOpt.get();
                updated.setActivityId(serviceDocument.getActivityId());
                if (updated.getName() == null) {
                    updated.setName(new ArrayList<>());
                }
                Helper.updateTranslation(serviceDocument.getName(), updated.getName());
                if (updated.getTiming() == null) {
                    updated.setTiming(updated.getTiming());
                }
                if (updated.getRefundable() == null) {
                    updated.setRefundable(updated.getRefundable());
                }
                if (updated.getHasInventory() == null) {
                    updated.setHasInventory(updated.getHasInventory());
                }
                if (updated.getStatus() == null) {
                    updated.setStatus(updated.getStatus());
                }
                if (updated.getInclusions() == null) {
                    updated.setInclusions(updated.getInclusions());
                }
                if (updated.getExclusions() == null) {
                    updated.setExclusions(updated.getExclusions());
                }
                updated.setLastModifiedDate(now);
                updated = serviceRepository.save(updated);

                return updated;
            }
        }
        serviceDocument.setLastModifiedDate(now);
        serviceDocument.setCreatedDate(now);
        serviceDocument.setLastModifiedBy(serviceDocument.getLastModifiedBy());
        serviceDocument = serviceRepository.save(serviceDocument);
        return serviceDocument;
    }

    @Override
    public Page<ServiceDocument> getServiceByActivityId(Long activityId, Pageable pageable) {
        Assert.notNull(activityId, "activityId must not be null");
        Query query = new Query().with(pageable);
        query.addCriteria(Criteria.where("activityId").is(activityId));
        List<ServiceDocument> list = mongoTemplate.find(query, ServiceDocument.class);
        LongSupplier total = () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), ServiceDocument.class);
        return PageableExecutionUtils.getPage(list, pageable, total);
    }

    @Override
    public void deleteService(Long serviceId) {
        Assert.notNull(serviceId, "serviceId must not be null");
        Optional<ServiceDocument> optionalServiceDocument = serviceRepository.findById(serviceId);
        if (optionalServiceDocument.isPresent()) {
            ServiceDocument serviceDocument = optionalServiceDocument.get();
            serviceDocument.setIsDelete(true);
            serviceRepository.save(serviceDocument);
        }
    }
}
