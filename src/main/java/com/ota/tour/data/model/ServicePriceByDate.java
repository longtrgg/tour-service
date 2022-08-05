package com.ota.tour.data.model;

import lombok.Data;

import java.util.List;

@Data
public class ServicePriceByDate {
    private String date;
    private List<ServicePriceAvailable> price;
}
