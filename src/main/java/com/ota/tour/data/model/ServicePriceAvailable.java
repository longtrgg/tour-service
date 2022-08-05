package com.ota.tour.data.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServicePriceAvailable implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long occupancyId;
    private Double originPrice;
    private Double price;
    private Currency currency;
    private Double convertRateToVND;
    private Double priceBeforePromo;
}