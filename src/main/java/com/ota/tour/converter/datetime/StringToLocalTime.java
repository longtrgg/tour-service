package com.ota.tour.converter.datetime;

import com.ota.tour.util.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;

public class StringToLocalTime implements Converter<String, LocalTime> {

    @Override
    public LocalTime convert(String time) {
        return DateUtils.parseTime(time);
    }
}
