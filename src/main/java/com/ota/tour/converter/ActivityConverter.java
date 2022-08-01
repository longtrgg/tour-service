package com.ota.tour.converter;

import com.ota.tour.data.document.ActivityDocument;
import com.ota.tour.data.model.TourActivityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class ActivityConverter {
    private final CommonConverter commonConverter;

    public ActivityDocument toActivityDocument(TourActivityDTO payload) {
        ActivityDocument activityDocument = new ActivityDocument();
        activityDocument.setId(payload.getId());
        activityDocument.setName(payload.getName());
        activityDocument.setNote(payload.getNote());
        activityDocument.setPolicy(payload.getPolicy());
        activityDocument.setOverview(payload.getOverview());
        activityDocument.setHowToUse(payload.getHowToUse());
        activityDocument.setImportantInformation(payload.getImportantInformation());
        activityDocument.setMeetUpAddress(payload.getMeetUpAddress());
        activityDocument.setMeetUpLocation(payload.getMeetUpLocation());
        activityDocument.setMeetUpInformation(payload.getMeetUpInformation());
        activityDocument.setDestinationIds(payload.getDestinationIds());
        activityDocument.setStatus(payload.getStatus());
        return activityDocument;
    }

    public TourActivityDTO toActivityResult(ActivityDocument activityDocument) {
        if (activityDocument == null || activityDocument.getIsDelete()) {
            return null;
        }
        TourActivityDTO tourActivityDTO = new TourActivityDTO();
        tourActivityDTO.setId(activityDocument.getId());
        if (activityDocument.getName() != null) {
            tourActivityDTO.setName(activityDocument.getName());
        }
        if (activityDocument.getNote() != null) {
            tourActivityDTO.setNote(activityDocument.getNote());
        }
        if (activityDocument.getPolicy() != null) {
            tourActivityDTO.setPolicy(activityDocument.getPolicy());
        }
        if (activityDocument.getOverview() != null) {
            tourActivityDTO.setOverview(activityDocument.getOverview());
        }
        if (activityDocument.getHowToUse() != null) {
            tourActivityDTO.setHowToUse(activityDocument.getHowToUse());
        }
        if (activityDocument.getImportantInformation() != null) {
            tourActivityDTO.setImportantInformation(activityDocument.getImportantInformation());
        }
        if (activityDocument.getMeetUpAddress() != null) {
            tourActivityDTO.setMeetUpAddress(activityDocument.getMeetUpAddress());
        }
        if (activityDocument.getMeetUpLocation() != null) {
            tourActivityDTO.setMeetUpLocation(activityDocument.getMeetUpLocation());
        }
        if (activityDocument.getMeetUpInformation() != null) {
            tourActivityDTO.setMeetUpInformation(activityDocument.getMeetUpInformation());
        }
        if (activityDocument.getDestinationIds() != null) {
            tourActivityDTO.setDestinationIds(activityDocument.getDestinationIds());
        }
        if (activityDocument.getStatus() != null) {
            tourActivityDTO.setStatus(activityDocument.getStatus());
        }
        if (activityDocument.getCreatedBy() != null) {
            tourActivityDTO.setCreatedBy(activityDocument.getCreatedBy());
        }
        if (activityDocument.getCreatedDate() != null) {
            tourActivityDTO.setCreatedDate(activityDocument.getCreatedDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        if (activityDocument.getLastModifiedBy() != null) {
            tourActivityDTO.setLastModifiedBy(activityDocument.getLastModifiedBy());
        }
        if (activityDocument.getLastModifiedDate() != null) {
            tourActivityDTO.setLastModifiedDate(activityDocument.getLastModifiedDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        return tourActivityDTO;
    }
}
