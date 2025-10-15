package com.ota.tour.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.ota.tour.converter.datetime.DateToZoneDateTime;
import com.ota.tour.converter.datetime.LocalTimeToString;
import com.ota.tour.converter.datetime.StringToLocalTime;
import com.ota.tour.converter.datetime.ZoneDateTimeToDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableMongoAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class MongodbConfiguration extends AbstractMongoClientConfiguration {

    private final Environment env;
    private final MongoProperties mongoProperties;

    @Override
    public MongoClient mongoClient() {
        String uri = mongoProperties.getUri();
        return MongoClients.create(uri);
    }

    @Bean
    @Override
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new LocalTimeToString(),
                new StringToLocalTime(),
                new ZoneDateTimeToDate(),
                new DateToZoneDateTime()
        ));
    }


    @Override
    protected String getDatabaseName() {
        return mongoProperties.getDatabase();
    }

    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(ZonedDateTime.now());
    }
}
