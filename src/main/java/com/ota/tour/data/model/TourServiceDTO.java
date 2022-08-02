package com.ota.tour.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TourServiceDTO extends TourBaseDTO implements Serializable {
    private List<Translation> name;
    private List<List<Translation>> inclusions;
    private List<List<Translation>> exclusions;
    private Boolean refundable;
    private Boolean hasInventory;
    private Boolean status;
    private Long activityId;
    private Timing timing;
}
