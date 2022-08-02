package com.ota.tour.data.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TourBaseDTO implements Serializable {
    protected Long id;
    protected String createdDate;
    protected String createdBy;
    protected String lastModifiedDate;
    protected String lastModifiedBy;
}

