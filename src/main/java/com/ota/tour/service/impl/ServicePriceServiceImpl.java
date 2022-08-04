package com.ota.tour.service.impl;

import com.ota.tour.data.document.ServiceDocument;
import com.ota.tour.data.document.ServicePriceDocument;
import com.ota.tour.data.model.ServicePrice;
import com.ota.tour.data.repository.ServicePriceRepository;
import com.ota.tour.data.repository.ServiceRepository;
import com.ota.tour.service.ServicePriceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.ota.tour.converter.CommonConverter.ZONE_ID_DEFAULT;

@Service
@AllArgsConstructor
public class ServicePriceServiceImpl implements ServicePriceService {
    private final ServicePriceRepository servicePriceRepository;
    private final ServiceRepository serviceRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public ServicePriceDocument saveOrUpdate(ServicePriceDocument servicePriceDocument) {
        Assert.notNull(servicePriceDocument, "tourServicePriceDocument must not be null");
        Assert.notNull(servicePriceDocument.getServiceId(), "tourServiceId must not be null");
        Assert.notNull(servicePriceDocument.getOccupancyId(), "tourOccupancyId must not be null");
        Assert.notNull(servicePriceDocument.getOccupancyId(), "tourActivityId must not be null");
        ZonedDateTime now = ZonedDateTime.now();
        servicePriceDocument.setLastModifiedDate(now);
        servicePriceDocument.setCreatedDate(now);
        servicePriceDocument.setLastModifiedBy(servicePriceDocument.getLastModifiedBy());
        servicePriceDocument = servicePriceRepository.save(servicePriceDocument);
        return servicePriceDocument;
    }

    @Override
    public Map<Long, Map<String, List<ServicePrice>>> findByActivityIdAndTimeRange(Long activityId, ZonedDateTime from, ZonedDateTime to) {
        Query query = new Query();
        query.addCriteria(Criteria.where("activityId").is(activityId));
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("validity.from").lte(to).and("validity.to").gte(to),
                Criteria.where("validity.from").lte(from).and("validity.to").gte(from));
        query.addCriteria(criteria);
        query.with(Sort.by(Sort.Direction.ASC, "id"));
        List<ServicePriceDocument> servicePriceDocumentList = mongoTemplate.find(query, ServicePriceDocument.class);
        long range = ChronoUnit.DAYS.between(from, to);
        Map<Long, List<ServicePriceDocument>> longListHashMap = new HashMap<>();
        for (ServicePriceDocument servicePriceDocument : servicePriceDocumentList) {
            Optional<ServiceDocument> serviceDocument = serviceRepository.findById(servicePriceDocument.getServiceId());
            if (serviceDocument.isPresent() && !serviceDocument.get().getIsDelete()) {
                if (!longListHashMap.containsKey(servicePriceDocument.getServiceId())) {
                    longListHashMap.put(servicePriceDocument.getServiceId(), new ArrayList<>());
                }
                longListHashMap.get(servicePriceDocument.getServiceId()).add(servicePriceDocument);
            }
        }

        Map<Long, Map<String, List<ServicePrice>>> result = new HashMap<>();
        for (Map.Entry<Long, List<ServicePriceDocument>> entry : longListHashMap.entrySet()) {
            Map<String, List<ServicePrice>> mapDateRatePlanPrice = new HashMap<>();
            for (int i = 0; i <= range; i++) {
                ZonedDateTime date = from.plusDays(i);
                Map<String, ServicePrice> mapRatePlanPrice = new HashMap<>();
                for (ServicePriceDocument servicePriceDocument : entry.getValue()) {
                    if ((servicePriceDocument.getValidity().getFrom().isAfter(date) || servicePriceDocument.getValidity().getTo().isBefore(date))
                            && (!servicePriceDocument.getValidity().getFrom().isEqual(date))
                            && (!servicePriceDocument.getValidity().getTo().isEqual(date))) {
                        continue;
                    }
                    if (!servicePriceDocument.getValidDaysOfWeek().contains(date.getDayOfWeek())) {
                        continue;
                    }
                    String key = servicePriceDocument.getServiceId() + "_" + servicePriceDocument.getOccupancyId();
                    if (!mapRatePlanPrice.containsKey(key)) {
                        ServicePrice servicePrice = new ServicePrice();
                        servicePrice.setServiceId(servicePriceDocument.getServiceId());
                        servicePrice.setConvertRateToVND(servicePriceDocument.getConvertRateToVND() == null ? 1 : servicePriceDocument.getConvertRateToVND());
                        servicePrice.setDate(date);
                        servicePrice.setCurrency(servicePriceDocument.getCurrency());
                        servicePrice.setPrice(servicePriceDocument.getPrice() == null ? 0 : servicePriceDocument.getPrice());
                        servicePrice.setOriginPrice(servicePriceDocument.getOriginPrice() == null ? 0 : servicePriceDocument.getOriginPrice());
                        servicePrice.setPriceBeforePromo(servicePriceDocument.getPriceBeforePromo() == null ? 0 : servicePriceDocument.getPriceBeforePromo());
                        servicePrice.setOccupancyId(servicePriceDocument.getOccupancyId());
                        mapRatePlanPrice.put(key, servicePrice);
                    } else {
                        ServicePrice servicePrice = mapRatePlanPrice.get(key);
                        servicePrice.setServiceId(servicePriceDocument.getServiceId());
                        servicePrice.setConvertRateToVND(servicePriceDocument.getConvertRateToVND() == null ? 1 : servicePriceDocument.getConvertRateToVND());
                        servicePrice.setDate(date);
                        servicePrice.setCurrency(servicePriceDocument.getCurrency());
                        servicePrice.setPrice(servicePriceDocument.getPrice() == null ? 0 : servicePriceDocument.getPrice());
                        servicePrice.setOriginPrice(servicePriceDocument.getOriginPrice() == null ? 0 : servicePriceDocument.getOriginPrice());
                        servicePrice.setPriceBeforePromo(servicePriceDocument.getPriceBeforePromo() == null ? 0 : servicePriceDocument.getPriceBeforePromo());
                        servicePrice.setOccupancyId(servicePriceDocument.getOccupancyId());
                        mapRatePlanPrice.replace(key, servicePrice);
                    }
                }
                mapDateRatePlanPrice.put(date.format(DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.of(ZONE_ID_DEFAULT))), new ArrayList<>(mapRatePlanPrice.values()));
            }
            result.put(entry.getKey(), mapDateRatePlanPrice);
        }
        return result;
    }


}
