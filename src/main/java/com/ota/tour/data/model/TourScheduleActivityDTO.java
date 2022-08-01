package com.ota.tour.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TourScheduleActivityDTO extends TourBaseDTO implements Serializable {
    private List<Translation> name;
    private List<Translation> description;
    private List<Translation> note;
    private String startAt;
    private Long scheduleId;
}
