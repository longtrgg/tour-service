package com.ota.tour.data.model;

import lombok.Data;

import java.util.List;

@Data
public class ActivityManagementPageResult {
    PageResult pageResult;
    List<TourActivityDTO> result;
}
