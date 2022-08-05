package com.ota.tour.service;

import com.ota.tour.data.document.InventoryDocument;

public interface InventoryService {

    InventoryDocument saveOrUpdate(InventoryDocument inventoryDocument);

//    Map<Long, Map<String, List<ServicePrice>>> findByActivityIdAndTimeRange(Long activityId, ZonedDateTime from, ZonedDateTime to);


}
