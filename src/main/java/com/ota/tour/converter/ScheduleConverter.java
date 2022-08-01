package com.ota.tour.converter;

import com.ota.tour.data.document.ScheduleDocument;
import com.ota.tour.data.model.TourScheduleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class ScheduleConverter {
    private final CommonConverter commonConverter;

    public ScheduleDocument toScheduleDocument(TourScheduleDTO payload) {
        ScheduleDocument scheduleDocument = new ScheduleDocument();
        scheduleDocument.setId(payload.getId());
        scheduleDocument.setName(payload.getName());
        scheduleDocument.setDescription(payload.getDescription());
        scheduleDocument.setActivityId(payload.getActivityId());
        return scheduleDocument;
    }

    public TourScheduleDTO toScheduleResult(ScheduleDocument scheduleDocument) {
        if (scheduleDocument == null || scheduleDocument.getIsDelete()) {
            return null;
        }
        TourScheduleDTO tourScheduleDTO = new TourScheduleDTO();
        tourScheduleDTO.setId(scheduleDocument.getId());
        if (scheduleDocument.getName() != null) {
            tourScheduleDTO.setName(scheduleDocument.getName());
        }
        if (scheduleDocument.getDescription() != null) {
            tourScheduleDTO.setDescription(scheduleDocument.getDescription());
        }
        if (scheduleDocument.getActivityId() != null) {
            tourScheduleDTO.setActivityId(scheduleDocument.getActivityId());
        }
        if (scheduleDocument.getCreatedBy() != null) {
            tourScheduleDTO.setCreatedBy(scheduleDocument.getCreatedBy());
        }
        if (scheduleDocument.getCreatedDate() != null) {
            tourScheduleDTO.setCreatedDate(scheduleDocument.getCreatedDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        if (scheduleDocument.getLastModifiedBy() != null) {
            tourScheduleDTO.setLastModifiedBy(scheduleDocument.getLastModifiedBy());
        }
        if (scheduleDocument.getLastModifiedDate() != null) {
            tourScheduleDTO.setLastModifiedDate(scheduleDocument.getLastModifiedDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        return tourScheduleDTO;
    }

}
