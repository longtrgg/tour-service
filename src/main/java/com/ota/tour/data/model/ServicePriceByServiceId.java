package com.ota.tour.data.model;

import lombok.Data;

import java.util.List;

@Data
public class ServicePriceByServiceId {
    private Long serviceId;
    private List<ServicePriceByDate> datePrices;
}
