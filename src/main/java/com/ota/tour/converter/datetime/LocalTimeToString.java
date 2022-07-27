package com.ota.tour.converter.datetime;

import com.ota.tour.util.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;

public class LocalTimeToString implements Converter<LocalTime, String> {

    @Override
    public String convert(LocalTime time) {
        return DateUtils.formatTime12Hours(time);
    }

}
