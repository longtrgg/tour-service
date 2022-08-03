package com.ota.tour.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TourOccupancyDTO extends TourBaseDTO implements Serializable {
    private Long activityId;
    private List<Translation> name;
    private List<Translation> description;
    private Integer minOccupancy;
    private Integer maxOccupancy;
    private Integer minAge;
    private Integer maxAge;
    private Integer minLengthCm;
    private Integer maxLengthCm;
    private Boolean primary;
    private OccupancyDescriptionType descriptionType;
}
