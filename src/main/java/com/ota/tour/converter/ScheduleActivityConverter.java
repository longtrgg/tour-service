package com.ota.tour.converter;

import com.ota.tour.data.document.ScheduleActivityDocument;
import com.ota.tour.data.model.TourScheduleActivityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class ScheduleActivityConverter {
    private final CommonConverter commonConverter;

    public ScheduleActivityDocument toScheduleActivityDocument(TourScheduleActivityDTO payload) {
        ScheduleActivityDocument scheduleActivityDocument = new ScheduleActivityDocument();
        scheduleActivityDocument.setId(payload.getId());
        scheduleActivityDocument.setName(payload.getName());
        scheduleActivityDocument.setDescription(payload.getDescription());
        scheduleActivityDocument.setScheduleId(payload.getScheduleId());
        scheduleActivityDocument.setStartAt(payload.getStartAt());
        scheduleActivityDocument.setNote(payload.getNote());
        return scheduleActivityDocument;
    }

    public TourScheduleActivityDTO toScheduleActivityResult(ScheduleActivityDocument scheduleActivityDocument) {
        if (scheduleActivityDocument == null || scheduleActivityDocument.getIsDelete()) {
            return null;
        }
        TourScheduleActivityDTO tourScheduleActivityDTO = new TourScheduleActivityDTO();
        tourScheduleActivityDTO.setId(scheduleActivityDocument.getId());
        if (scheduleActivityDocument.getName() != null) {
            tourScheduleActivityDTO.setName(scheduleActivityDocument.getName());
        }
        if (scheduleActivityDocument.getDescription() != null) {
            tourScheduleActivityDTO.setDescription(scheduleActivityDocument.getDescription());
        }
        if (scheduleActivityDocument.getNote() != null) {
            tourScheduleActivityDTO.setNote(scheduleActivityDocument.getNote());
        }
        if (scheduleActivityDocument.getStartAt() != null) {
            tourScheduleActivityDTO.setStartAt(scheduleActivityDocument.getStartAt());
        }
        if (scheduleActivityDocument.getScheduleId() != null) {
            tourScheduleActivityDTO.setScheduleId(scheduleActivityDocument.getScheduleId());
        }
        if (scheduleActivityDocument.getCreatedBy() != null) {
            tourScheduleActivityDTO.setCreatedBy(scheduleActivityDocument.getCreatedBy());
        }
        if (scheduleActivityDocument.getCreatedDate() != null) {
            tourScheduleActivityDTO.setCreatedDate(scheduleActivityDocument.getCreatedDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        if (scheduleActivityDocument.getLastModifiedBy() != null) {
            tourScheduleActivityDTO.setLastModifiedBy(scheduleActivityDocument.getLastModifiedBy());
        }
        if (scheduleActivityDocument.getLastModifiedDate() != null) {
            tourScheduleActivityDTO.setLastModifiedDate(scheduleActivityDocument.getLastModifiedDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        return tourScheduleActivityDTO;
    }

}
