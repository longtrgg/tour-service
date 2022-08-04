package com.ota.tour.data.model;

import lombok.Data;

@Data
public class ServicePriceByDate {
    private Double originPrice;
    private Double price;
    private Currency currency;
    private Double convertRateToVND;
    private Double priceBeforePromo;
}
