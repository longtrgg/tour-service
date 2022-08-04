package com.ota.tour.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TourServicePriceDTO extends TourBaseDTO implements Serializable {
    private Long serviceId;
    private Long occupancyId;
    private Double originPrice;
    private Double price;
    private Currency currency;
    private Double convertRateToVND;
    private DateTimeRange validity;
    private List<DayOfWeek> validDaysOfWeek;
    private Double priceBeforePromo;
}
