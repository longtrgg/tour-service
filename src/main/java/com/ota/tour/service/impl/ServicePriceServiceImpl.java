package com.ota.tour.service.impl;

import com.ota.tour.data.document.ServicePriceDocument;
import com.ota.tour.data.repository.ServicePriceRepository;
import com.ota.tour.service.ServicePriceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
public class ServicePriceServiceImpl implements ServicePriceService {
    private final ServicePriceRepository servicePriceRepository;

    @Override
    public ServicePriceDocument saveOrUpdate(ServicePriceDocument servicePriceDocument) {
        Assert.notNull(servicePriceDocument, "tourServicePriceDocument must not be null");
        Assert.notNull(servicePriceDocument.getServiceId(), "tourServiceId must not be null");
        Assert.notNull(servicePriceDocument.getOccupancyId(), "tourOccupancyId must not be null");
        ZonedDateTime now = ZonedDateTime.now();
        servicePriceDocument.setLastModifiedDate(now);
        servicePriceDocument.setCreatedDate(now);
        servicePriceDocument.setLastModifiedBy(servicePriceDocument.getLastModifiedBy());
        servicePriceDocument = servicePriceRepository.save(servicePriceDocument);
        return servicePriceDocument;
    }


}
