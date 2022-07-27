package com.ota.tour.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timing implements Serializable {
    private Boolean isMultiDays;
    private Integer durationDays;
    private Integer durationNights;
    private Integer durationHours;
}
