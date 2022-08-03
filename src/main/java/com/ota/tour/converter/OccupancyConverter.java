package com.ota.tour.converter;

import com.ota.tour.data.document.OccupancyDocument;
import com.ota.tour.data.model.TourOccupancyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class OccupancyConverter {
    private final CommonConverter commonConverter;

    public OccupancyDocument toOccupancyDocument(TourOccupancyDTO payload) {
        OccupancyDocument occupancyDocument = new OccupancyDocument();
        occupancyDocument.setId(payload.getId());
        occupancyDocument.setName(payload.getName());
        occupancyDocument.setDescription(payload.getDescription());
        occupancyDocument.setMaxOccupancy(payload.getMaxOccupancy());
        occupancyDocument.setMinOccupancy(payload.getMinOccupancy());
        occupancyDocument.setMaxLengthCm(payload.getMaxLengthCm());
        occupancyDocument.setMinLengthCm(payload.getMinLengthCm());
        occupancyDocument.setMaxAge(payload.getMaxAge());
        occupancyDocument.setMinAge(payload.getMinAge());
        occupancyDocument.setPrimary(payload.getPrimary());
        occupancyDocument.setDescriptionType(payload.getDescriptionType());
        occupancyDocument.setActivityId(payload.getActivityId());
        return occupancyDocument;
    }

    public TourOccupancyDTO toOccupancyResult(OccupancyDocument occupancyDocument) {
        if (occupancyDocument == null || occupancyDocument.getIsDelete()) {
            return null;
        }
        TourOccupancyDTO tourOccupancyDTO = new TourOccupancyDTO();
        tourOccupancyDTO.setId(occupancyDocument.getId());
        if (occupancyDocument.getName() != null) {
            tourOccupancyDTO.setName(occupancyDocument.getName());
        }
        if (occupancyDocument.getDescription() != null) {
            tourOccupancyDTO.setDescription(occupancyDocument.getDescription());
        }
        if (occupancyDocument.getMaxOccupancy() != null) {
            tourOccupancyDTO.setMaxOccupancy(occupancyDocument.getMaxOccupancy());
        }
        if (occupancyDocument.getMinOccupancy() != null) {
            tourOccupancyDTO.setMinOccupancy(occupancyDocument.getMinOccupancy());
        }
        if (occupancyDocument.getMaxLengthCm() != null) {
            tourOccupancyDTO.setMaxLengthCm(occupancyDocument.getMaxLengthCm());
        }
        if (occupancyDocument.getMinLengthCm() != null) {
            tourOccupancyDTO.setMinLengthCm(occupancyDocument.getMinLengthCm());
        }
        if (occupancyDocument.getMaxAge() != null) {
            tourOccupancyDTO.setMaxAge(occupancyDocument.getMaxAge());
        }
        if (occupancyDocument.getMinAge() != null) {
            tourOccupancyDTO.setMinAge(occupancyDocument.getMinAge());
        }
        if (occupancyDocument.getPrimary() != null) {
            tourOccupancyDTO.setPrimary(occupancyDocument.getPrimary());
        }
        if (occupancyDocument.getDescriptionType() != null) {
            tourOccupancyDTO.setDescriptionType(occupancyDocument.getDescriptionType());
        }
        if (occupancyDocument.getActivityId() != null) {
            tourOccupancyDTO.setActivityId(occupancyDocument.getActivityId());
        }
        if (occupancyDocument.getCreatedBy() != null) {
            tourOccupancyDTO.setCreatedBy(occupancyDocument.getCreatedBy());
        }
        if (occupancyDocument.getCreatedDate() != null) {
            tourOccupancyDTO.setCreatedDate(occupancyDocument.getCreatedDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        if (occupancyDocument.getLastModifiedBy() != null) {
            tourOccupancyDTO.setLastModifiedBy(occupancyDocument.getLastModifiedBy());
        }
        if (occupancyDocument.getLastModifiedDate() != null) {
            tourOccupancyDTO.setLastModifiedDate(occupancyDocument.getLastModifiedDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        return tourOccupancyDTO;
    }

}
