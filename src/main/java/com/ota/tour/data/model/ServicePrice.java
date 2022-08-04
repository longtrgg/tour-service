package com.ota.tour.data.model;

import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
public class ServicePrice implements Serializable {
    private static final long serialVersionUID = 1L;

    private ZonedDateTime date;
    private Long serviceId;
    private Long occupancyId;
    private Long activityId;
    private Double originPrice;
    private Double price;
    private Currency currency;
    private Double convertRateToVND;
    private Double priceBeforePromo;
}