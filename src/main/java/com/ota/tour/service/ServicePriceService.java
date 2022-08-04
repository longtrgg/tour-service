package com.ota.tour.service;

import com.ota.tour.data.document.ServicePriceDocument;
import com.ota.tour.data.model.ServicePrice;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public interface ServicePriceService {

    ServicePriceDocument saveOrUpdate(ServicePriceDocument servicePriceDocument);

    Map<Long, Map<String, List<ServicePrice>>> findByActivityIdAndTimeRange(Long activityId, ZonedDateTime from, ZonedDateTime to);


}
