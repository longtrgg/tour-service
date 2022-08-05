package com.ota.tour.service.impl;

import com.ota.tour.data.document.InventoryDocument;
import com.ota.tour.data.repository.InventoryRepository;
import com.ota.tour.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public InventoryDocument saveOrUpdate(InventoryDocument inventoryDocument) {
        Assert.notNull(inventoryDocument, "tourInventoryDocument must not be null");
        Assert.notNull(inventoryDocument.getServiceId(), "tourServiceId must not be null");
        Assert.notNull(inventoryDocument.getActivityId(), "tourActivityId must not be null");
        ZonedDateTime now = ZonedDateTime.now();
        inventoryDocument.setLastModifiedDate(now);
        inventoryDocument.setCreatedDate(now);
        inventoryDocument.setLastModifiedBy(inventoryDocument.getLastModifiedBy());
        inventoryDocument = inventoryRepository.save(inventoryDocument);
        return inventoryDocument;
    }

//    @Override
//    public Map<Long, Map<String, List<ServicePrice>>> findByActivityIdAndTimeRange(Long activityId, ZonedDateTime from, ZonedDateTime to) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("activityId").is(activityId));
//        Criteria criteria = new Criteria();
//        criteria.orOperator(Criteria.where("validity.from").lte(to).and("validity.to").gte(to),
//                Criteria.where("validity.from").lte(from).and("validity.to").gte(from));
//        query.addCriteria(criteria);
//        query.with(Sort.by(Sort.Direction.ASC, "id"));
//        List<InventoryDocument> inventoryDocumentList = mongoTemplate.find(query, InventoryDocument.class);
//        long range = ChronoUnit.DAYS.between(from, to);
//        Map<Long, List<InventoryDocument>> longListHashMap = new HashMap<>();
//        for (InventoryDocument inventoryDocument : inventoryDocumentList) {
//            Optional<ServiceDocument> serviceDocument = serviceRepository.findById(inventoryDocument.getServiceId());
//            if (serviceDocument.isPresent() && !serviceDocument.get().getIsDelete()) {
//                if (!longListHashMap.containsKey(inventoryDocument.getServiceId())) {
//                    longListHashMap.put(inventoryDocument.getServiceId(), new ArrayList<>());
//                }
//                longListHashMap.get(inventoryDocument.getServiceId()).add(inventoryDocument);
//            }
//        }
//
//        Map<Long, Map<String, List<ServicePrice>>> result = new HashMap<>();
//        for (Map.Entry<Long, List<InventoryDocument>> entry : longListHashMap.entrySet()) {
//            Map<String, List<ServicePrice>> mapDateRatePlanPrice = new HashMap<>();
//            for (int i = 0; i <= range; i++) {
//                ZonedDateTime date = from.plusDays(i);
//                Map<String, ServicePrice> mapRatePlanPrice = new HashMap<>();
//                for (InventoryDocument inventoryDocument : entry.getValue()) {
//                    if ((inventoryDocument.getValidity().getFrom().isAfter(date) || inventoryDocument.getValidity().getTo().isBefore(date))
//                            && (!inventoryDocument.getValidity().getFrom().isEqual(date))
//                            && (!inventoryDocument.getValidity().getTo().isEqual(date))) {
//                        continue;
//                    }
//                    if (!inventoryDocument.getValidDaysOfWeek().contains(date.getDayOfWeek())) {
//                        continue;
//                    }
//                    String key = inventoryDocument.getServiceId() + "_" + inventoryDocument.getOccupancyId();
//                    if (!mapRatePlanPrice.containsKey(key)) {
//                        ServicePrice servicePrice = new ServicePrice();
//                        servicePrice.setServiceId(inventoryDocument.getServiceId());
//                        servicePrice.setConvertRateToVND(inventoryDocument.getConvertRateToVND() == null ? 1 : inventoryDocument.getConvertRateToVND());
//                        servicePrice.setDate(date);
//                        servicePrice.setCurrency(inventoryDocument.getCurrency());
//                        servicePrice.setPrice(inventoryDocument.getPrice() == null ? 0 : inventoryDocument.getPrice());
//                        servicePrice.setOriginPrice(inventoryDocument.getOriginPrice() == null ? 0 : inventoryDocument.getOriginPrice());
//                        servicePrice.setPriceBeforePromo(inventoryDocument.getPriceBeforePromo() == null ? 0 : inventoryDocument.getPriceBeforePromo());
//                        servicePrice.setOccupancyId(inventoryDocument.getOccupancyId());
//                        mapRatePlanPrice.put(key, servicePrice);
//                    } else {
//                        ServicePrice servicePrice = mapRatePlanPrice.get(key);
//                        servicePrice.setServiceId(inventoryDocument.getServiceId());
//                        servicePrice.setConvertRateToVND(inventoryDocument.getConvertRateToVND() == null ? 1 : inventoryDocument.getConvertRateToVND());
//                        servicePrice.setDate(date);
//                        servicePrice.setCurrency(inventoryDocument.getCurrency());
//                        servicePrice.setPrice(inventoryDocument.getPrice() == null ? 0 : inventoryDocument.getPrice());
//                        servicePrice.setOriginPrice(inventoryDocument.getOriginPrice() == null ? 0 : inventoryDocument.getOriginPrice());
//                        servicePrice.setPriceBeforePromo(inventoryDocument.getPriceBeforePromo() == null ? 0 : inventoryDocument.getPriceBeforePromo());
//                        servicePrice.setOccupancyId(inventoryDocument.getOccupancyId());
//                        mapRatePlanPrice.replace(key, servicePrice);
//                    }
//                }
//                mapDateRatePlanPrice.put(date.format(DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.of(ZONE_ID_DEFAULT))), new ArrayList<>(mapRatePlanPrice.values()));
//            }
//            result.put(entry.getKey(), mapDateRatePlanPrice);
//        }
//        return result;
//    }


}
