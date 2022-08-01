package com.ota.tour.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TourActivityDTO extends TourBaseDTO implements Serializable {
    private List<Translation> name;
    private List<Translation> overview;
    private List<Translation> importantInformation;
    private List<Translation> note;
    private List<Translation> policy;
    private List<Translation> howToUse;
    private List<Translation> meetUpInformation;
    private List<Translation> meetUpAddress;
    private GeoJsonPoint meetUpLocation;
    private LinkedHashSet<Long> destinationIds;
    private Boolean status;
}
