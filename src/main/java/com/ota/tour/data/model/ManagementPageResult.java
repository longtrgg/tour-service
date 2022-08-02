package com.ota.tour.data.model;

import lombok.Data;

import java.util.List;

@Data
public class ManagementPageResult<T> {
    PageResult pageResult;
    List<T> result;
}
