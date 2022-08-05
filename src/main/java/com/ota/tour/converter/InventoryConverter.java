package com.ota.tour.converter;

import com.ota.tour.data.document.InventoryDocument;
import com.ota.tour.data.model.DateTimeRange;
import com.ota.tour.data.model.TourInventoryDTO;
import com.ota.tour.data.model.ZoneDateTimeRange;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.ota.tour.converter.CommonConverter.FORMAT_LOCAL_DATE;
import static com.ota.tour.converter.CommonConverter.ZONE_ID_DEFAULT;

@Component
@RequiredArgsConstructor
public class InventoryConverter {
    private final CommonConverter commonConverter;

    public InventoryDocument toInventoryDocument(TourInventoryDTO payload) {
        InventoryDocument inventoryDocument = new InventoryDocument();
        inventoryDocument.setId(payload.getId());
        inventoryDocument.setActivityId(payload.getActivityId());
        inventoryDocument.setServiceId(payload.getServiceId());
        inventoryDocument.setBlock(payload.getBlock());
        inventoryDocument.setAvailable(payload.getAvailable());
        inventoryDocument.setValidity(toValidity(payload.getValidity()));
        inventoryDocument.setValidDaysOfWeek(payload.getValidDaysOfWeek());
        return inventoryDocument;
    }

    public TourInventoryDTO toInventoryResult(InventoryDocument inventoryDocument) {
        if (inventoryDocument == null) {
            return null;
        }
        TourInventoryDTO tourInventoryDTO = new TourInventoryDTO();
        tourInventoryDTO.setId(inventoryDocument.getId());
        tourInventoryDTO.setServiceId(inventoryDocument.getServiceId());
        tourInventoryDTO.setActivityId(inventoryDocument.getActivityId());
        if (inventoryDocument.getAvailable() != null) {
            tourInventoryDTO.setAvailable(inventoryDocument.getAvailable());
        }
        if (inventoryDocument.getBlock() != null) {
            tourInventoryDTO.setBlock(inventoryDocument.getBlock());
        }

        if (inventoryDocument.getValidity() != null) {
            tourInventoryDTO.setValidity(toDateTimeRange(inventoryDocument.getValidity()));
        }
        if (inventoryDocument.getValidDaysOfWeek() != null) {
            tourInventoryDTO.setValidDaysOfWeek(inventoryDocument.getValidDaysOfWeek());
        }
        if (inventoryDocument.getCreatedBy() != null) {
            tourInventoryDTO.setCreatedBy(inventoryDocument.getCreatedBy());
        }
        if (inventoryDocument.getCreatedDate() != null) {
            tourInventoryDTO.setCreatedDate(inventoryDocument.getCreatedDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        if (inventoryDocument.getLastModifiedBy() != null) {
            tourInventoryDTO.setLastModifiedBy(inventoryDocument.getLastModifiedBy());
        }
        if (inventoryDocument.getLastModifiedDate() != null) {
            tourInventoryDTO.setLastModifiedDate(inventoryDocument.getLastModifiedDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        return tourInventoryDTO;
    }

    public ZoneDateTimeRange toValidity(DateTimeRange dateTimeRange) {
        ZoneDateTimeRange zoneDateTimeRange = new ZoneDateTimeRange();
        ZoneId zoneId = ZoneId.of(ZONE_ID_DEFAULT);
        if (dateTimeRange.getFrom() != null && dateTimeRange.getFrom() != " ") {
            ZonedDateTime from = ZonedDateTime.of(LocalDate.parse(dateTimeRange.getFrom(),
                    DateTimeFormatter.ofPattern(FORMAT_LOCAL_DATE)), LocalTime.MIN, zoneId);
            zoneDateTimeRange.setFrom(from);
        }
        if (dateTimeRange.getTo() != null && dateTimeRange.getTo() != " ") {
            ZonedDateTime to = ZonedDateTime.of(LocalDate.parse(dateTimeRange.getTo(),
                    DateTimeFormatter.ofPattern(FORMAT_LOCAL_DATE)), LocalTime.MIN, zoneId);
            zoneDateTimeRange.setTo(to);
        }
        return zoneDateTimeRange;
    }

    public DateTimeRange toDateTimeRange(ZoneDateTimeRange zoneDateTimeRange) {
        if (zoneDateTimeRange == null) {
            return null;
        }
        DateTimeRange dateTimeRange = new DateTimeRange();
        if (zoneDateTimeRange.getFrom() != null) {
            dateTimeRange.setFrom(zoneDateTimeRange.getFrom().format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
        if (zoneDateTimeRange.getTo() != null) {
            dateTimeRange.setTo(zoneDateTimeRange.getTo().format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
        return dateTimeRange;
    }
}