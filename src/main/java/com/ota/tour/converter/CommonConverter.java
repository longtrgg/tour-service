package com.ota.tour.converter;

import com.ota.tour.data.model.ManagementPageResult;
import com.ota.tour.data.model.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public <T, S> ManagementPageResult<T> toPageResult(Page<S> documentPage, List<T> resultList) {
        if (documentPage == null) {
            return null;
        }
        ManagementPageResult<T> managementPageResult = new ManagementPageResult();
        managementPageResult.setPageResult(toPageResult(documentPage.getSize(), documentPage.getNumber(), documentPage.getTotalPages(), documentPage.getTotalElements()));
        managementPageResult.setResult(resultList);
        return managementPageResult;
    }

    public <T> ManagementPageResult<T> toPageResult(List<T> resultList) {
        ManagementPageResult<T> managementPageResult = new ManagementPageResult();
        managementPageResult.setResult(resultList);
        return managementPageResult;
    }
}
