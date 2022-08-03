package com.ota.tour.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ota.tour.converter.json.ZoneDateTimeDeserializer;
import com.ota.tour.converter.json.ZoneDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZoneDateTimeRange implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonSerialize(using = ZoneDateTimeSerializer.class)
    @JsonDeserialize(using = ZoneDateTimeDeserializer.class)
    private ZonedDateTime from;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonSerialize(using = ZoneDateTimeSerializer.class)
    @JsonDeserialize(using = ZoneDateTimeDeserializer.class)
    private ZonedDateTime to;
}
