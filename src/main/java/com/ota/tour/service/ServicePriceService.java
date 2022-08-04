package com.ota.tour.service;

import com.ota.tour.data.document.ServicePriceDocument;

public interface ServicePriceService {

    ServicePriceDocument saveOrUpdate(ServicePriceDocument servicePriceDocument);

//    Page<ServicePriceDocument> getServiceByActivityId(Long activityId, Pageable pageable);


}
