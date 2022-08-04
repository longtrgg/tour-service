package com.ota.tour.converter;

import com.ota.tour.data.document.ServicePriceDocument;
import com.ota.tour.data.model.DateTimeRange;
import com.ota.tour.data.model.TourServicePriceDTO;
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
public class ServicePriceConverter {
    private final CommonConverter commonConverter;

    public ServicePriceDocument toServicePriceDocument(TourServicePriceDTO payload) {
        ServicePriceDocument servicePriceDocument = new ServicePriceDocument();
        servicePriceDocument.setId(payload.getId());
        servicePriceDocument.setServiceId(payload.getServiceId());
        servicePriceDocument.setOccupancyId(payload.getOccupancyId());
        servicePriceDocument.setPrice(payload.getPrice());
        servicePriceDocument.setOriginPrice(payload.getOriginPrice());
        servicePriceDocument.setPriceBeforePromo(payload.getPriceBeforePromo());
        servicePriceDocument.setCurrency(payload.getCurrency());
        servicePriceDocument.setConvertRateToVND(payload.getConvertRateToVND());
        servicePriceDocument.setValidity(toValidity(payload.getValidity()));
        servicePriceDocument.setValidDaysOfWeek(payload.getValidDaysOfWeek());
        return servicePriceDocument;
    }

    public TourServicePriceDTO toServicePriceResult(ServicePriceDocument servicePriceDocument) {
        if (servicePriceDocument == null) {
            return null;
        }
        TourServicePriceDTO tourServiceDTO = new TourServicePriceDTO();
        tourServiceDTO.setId(servicePriceDocument.getId());
        tourServiceDTO.setServiceId(servicePriceDocument.getServiceId());
        tourServiceDTO.setOccupancyId(servicePriceDocument.getOccupancyId());
        if (servicePriceDocument.getPrice() != null) {
            tourServiceDTO.setPrice(servicePriceDocument.getPrice());
        }
        if (servicePriceDocument.getOriginPrice() != null) {
            tourServiceDTO.setOriginPrice(servicePriceDocument.getOriginPrice());
        }
        if (servicePriceDocument.getPriceBeforePromo() != null) {
            tourServiceDTO.setPriceBeforePromo(servicePriceDocument.getPriceBeforePromo());
        }
        if (servicePriceDocument.getCurrency() != null) {
            tourServiceDTO.setCurrency(servicePriceDocument.getCurrency());
        }
        if (servicePriceDocument.getConvertRateToVND() != null) {
            tourServiceDTO.setConvertRateToVND(servicePriceDocument.getConvertRateToVND());
        }
        if (servicePriceDocument.getValidity() != null) {
            tourServiceDTO.setValidity(toDateTimeRange(servicePriceDocument.getValidity()));
        }
        if (servicePriceDocument.getValidDaysOfWeek() != null) {
            tourServiceDTO.setValidDaysOfWeek(servicePriceDocument.getValidDaysOfWeek());
        }
        if (servicePriceDocument.getCreatedBy() != null) {
            tourServiceDTO.setCreatedBy(servicePriceDocument.getCreatedBy());
        }
        if (servicePriceDocument.getCreatedDate() != null) {
            tourServiceDTO.setCreatedDate(servicePriceDocument.getCreatedDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        if (servicePriceDocument.getLastModifiedBy() != null) {
            tourServiceDTO.setLastModifiedBy(servicePriceDocument.getLastModifiedBy());
        }
        if (servicePriceDocument.getLastModifiedDate() != null) {
            tourServiceDTO.setLastModifiedDate(servicePriceDocument.getLastModifiedDate().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
        return tourServiceDTO;
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
