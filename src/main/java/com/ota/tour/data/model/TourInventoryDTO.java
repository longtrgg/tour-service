package com.ota.tour.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TourInventoryDTO extends TourBaseDTO implements Serializable {
    private Long activityId;
    private Long serviceId;
    private DateTimeRange validity;
    private List<DayOfWeek> validDaysOfWeek;
    private Boolean block;
    private Integer available;
}
