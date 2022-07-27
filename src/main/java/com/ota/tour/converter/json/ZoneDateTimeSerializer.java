package com.ota.tour.converter.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZoneDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

    @Override
    public void serialize(ZonedDateTime time, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        generator.writeString(time.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
    }
}
