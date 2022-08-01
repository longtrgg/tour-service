package com.ota.tour.converter;

import com.ota.tour.data.document.ServiceDocument;
import com.ota.tour.data.model.TourServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class ServiceConverter {
    private final CommonConverter commonConverter;

    public ServiceDocument toServiceDocument(TourServiceDTO payload) {
        ServiceDocument serviceDocument = new ServiceDocument();
        serviceDocument.setId(payload.getId());
        serviceDocument.setName(payload.getName());
        serviceDocument.setInclusions(payload.getInclusions());
        serviceDocument.setExclusions(payload.getExclusions());
        serviceDocument.setRefundable(payload.getRefundable());
        serviceDocument.setStatus(payload.getStatus());
        serviceDocument.setHasInventory(payload.getHasInventory());
        serviceDocument.setTiming(payload.getTiming());
        serviceDocument.setActivityId(payload.getActivityId());
        return serviceDocument;
    }

    public TourServiceDTO toServiceResult(ServiceDocument serviceDocument) {
        if (serviceDocument == null || serviceDocument.getIsDelete()) {
            return null;
        }
        TourServiceDTO tourServiceDTO = new TourServiceDTO();
        tourServiceDTO.setId(serviceDocument.getId());
        if (serviceDocument.getName() != null) {
            tourServiceDTO.setName(serviceDocument.getName());
        }
        if (serviceDocument.getInclusions() != null) {
            tourServiceDTO.setInclusions(serviceDocument.getInclusions());
        }
        if (serviceDocument.getExclusions() != null) {
            tourServiceDTO.setExclusions(serviceDocument.getExclusions());
        }
        if (serviceDocument.getStatus() != null) {
            tourServiceDTO.setStatus(serviceDocument.getStatus());
        }
        if (serviceDocument.getRefundable() != null) {
            tourServiceDTO.setRefundable(serviceDocument.getRefundable());
        }
        if (serviceDocument.getHasInventory() != null) {
            tourServiceDTO.setHasInventory(serviceDocument.getHasInventory());
        }
        if (serviceDocument.getTiming() != null) {
            tourServiceDTO.setTiming(serviceDocument.getTiming());
        }
        if (serviceDocument.getActivityId() != null) {
            tourServiceDTO.setActivityId(serviceDocument.getActivityId());
        }
        if (serviceDocument.getCreatedBy() != null) {
            tourServiceDTO.setCreatedBy(serviceDocument.getCreatedBy());
        }
        if (serviceDocument.getCreatedDate() != null) {
            tourServiceDTO.setCreatedDate(serviceDocument.getCreatedDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        if (serviceDocument.getLastModifiedBy() != null) {
            tourServiceDTO.setLastModifiedBy(serviceDocument.getLastModifiedBy());
        }
        if (serviceDocument.getLastModifiedDate() != null) {
            tourServiceDTO.setLastModifiedDate(serviceDocument.getLastModifiedDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        return tourServiceDTO;
    }

}
