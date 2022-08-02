package com.ota.tour.data.model;

import lombok.Data;

@Data
public class PageResult {
    int pageSize;
    int pageNumber;
    int totalPage;
    long totalItems;
}
