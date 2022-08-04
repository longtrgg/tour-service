package com.ota.tour.converter;

import com.ota.tour.data.document.ServicePriceDocument;
import com.ota.tour.data.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.ota.tour.converter.CommonConverter.FORMAT_LOCAL_DATE;
import static com.ota.tour.converter.CommonConverter.ZONE_ID_DEFAULT;

@Component
@RequiredArgsConstructor
public class ServicePriceConverter {
    private final CommonConverter commonConverter;

    public ServicePriceDocument toServicePriceDocument(TourServicePriceDTO payload) {
        ServicePriceDocument servicePriceDocument = new ServicePriceDocument();
        servicePriceDocument.setId(payload.getId());
        servicePriceDocument.setActivityId(payload.getActivityId());
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
        tourServiceDTO.setActivityId(servicePriceDocument.getActivityId());
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

    public ServicePriceResult toDatePriceResult(Map<Long, Map<String, List<ServicePrice>>> map) {
        ServicePriceResult servicePriceResult = new ServicePriceResult();
        servicePriceResult.setResult(toServicePriceByServiceId(map));
        return servicePriceResult;
    }

    public List<ServicePriceByServiceId> toServicePriceByServiceId(Map<Long, Map<String, List<ServicePrice>>> map) {
        if (map == null) {
            return null;
        }
        return map.entrySet().stream().map(entry -> toPriceByServiceId(entry.getKey(), entry.getValue()))
                .filter(Objects::nonNull).collect(Collectors.toList());
    }

    public ServicePriceByServiceId toPriceByServiceId(Long serviceId, Map<String, List<ServicePrice>> map) {
        if (map == null) {
            return null;
        }
        ServicePriceByServiceId datePrice = new ServicePriceByServiceId();
        datePrice.setServiceId(serviceId);
        datePrice.setDatePrices(map.entrySet().stream().map(entry -> toPriceByDate(entry.getKey(), entry.getValue()))
                .filter(Objects::nonNull).collect(Collectors.toList()));
        return datePrice;
    }

    public ServicePriceByDate toPriceByDate(String date, List<ServicePrice> prices) {
        ServicePriceByDate priceByDate = new ServicePriceByDate();
        priceByDate.setDate(date);
        priceByDate.setPrice(prices.stream().map(this::toPrice)
                .filter(Objects::nonNull).collect(Collectors.toList()));
        return priceByDate;
    }

    public ServicePriceDetail toPrice(ServicePrice price) {
        ServicePriceDetail servicePriceDetail = new ServicePriceDetail();
        if (price == null) {
            return null;
        }
        if (price.getOriginPrice() != null) {
            servicePriceDetail.setOriginPrice(price.getOriginPrice());
        }
        if (price.getCurrency() != null) {
            servicePriceDetail.setCurrency(price.getCurrency());
        }
        if (price.getPriceBeforePromo() != null) {
            servicePriceDetail.setPriceBeforePromo(price.getPriceBeforePromo());
        }
        if (price.getPrice() != null) {
            servicePriceDetail.setPrice(price.getPrice());
        }
        if (price.getOccupancyId() != null) {
            servicePriceDetail.setOccupancyId(price.getOccupancyId());
        }
        if (price.getConvertRateToVND() != null) {
            servicePriceDetail.setConvertRateToVND(price.getConvertRateToVND());
        }
        return servicePriceDetail;
    }

}
