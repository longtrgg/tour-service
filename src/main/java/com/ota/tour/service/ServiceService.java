package com.ota.tour.service;

import com.ota.tour.data.document.ServiceDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceService {

    ServiceDocument saveOrUpdate(ServiceDocument serviceDocument);

    Page<ServiceDocument> getServiceByActivityId(Long activityId, Pageable pageable);

    void deleteService(Long serviceId);

}
