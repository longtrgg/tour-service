package com.ota.tour.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Translation implements Serializable {
    private static final long serialVersionUID = 1L;

    private Language language;
    private String value;
}
