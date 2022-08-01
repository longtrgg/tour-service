package com.ota.tour.converter;

import com.ota.tour.data.model.PageResult;
import org.springframework.stereotype.Component;

@Component
public class CommonConverter {
    public static final Integer PAGE_NUMBER_DEFAULT = 0;
    public static final Integer PAGE_SIZE_DEFAULT = 20;
    public static final String ZONE_ID_DEFAULT = "UTC";
    public static final String FORMAT_LOCAL_DATE = "yyyy-MM-dd";
    public static final String FORMAT_LOCAL_TIME = "HH:mm";

    public PageResult toPageResult(
            int pageSize,
            int pageNumber,
            int totalPage,
            long totalItems
    ) {
        PageResult managementPageResult = new PageResult();
        managementPageResult.setPageNumber(pageNumber);
        managementPageResult.setPageSize(pageSize);
        managementPageResult.setTotalPage(totalPage);
        managementPageResult.setTotalItems(totalItems);
        return managementPageResult;
    }
}
