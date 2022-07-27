package com.ota.tour.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final ZoneId ZONE_HCM = ZoneId.of("Asia/Ho_Chi_Minh");
    public static final DateTimeFormatter TIME_12HOURS_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");
    public static final DateTimeFormatter TIME_24HOURS_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final String TIME_12HOURS_SHORT_PATTERN = "[1-9]:[0-5][0-9](\\s)?(?i)(am|pm)";
    public static final String TIME_12HOURS_PATTERN = "([0]?[0-9]|[1][0-2]):[0-5][0-9](\\s)?(?i)(am|pm)";
    public static final String TIME_24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    public static final String TIME_24HOURS_SHORT_PATTERN = "[1-9]:[0-5][0-9]";
    public static final DateTimeFormatter ZONE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    public static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter LOCAL_DATE_FORMATTER_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter FULL_TIME_ZONE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

    private DateUtils() {
    }

    public static LocalTime parseTime(String time) {
        time = time.trim();
        if (RegexUtils.validate(TIME_12HOURS_PATTERN, time)) {
            return parseTime12Hours(time);
        }
        if (RegexUtils.validate(TIME_24HOURS_PATTERN, time)) {
            return parseTime24Hours(time);
        }

        throw new IllegalArgumentException(String.join(" ", time, "invalid"));
    }

    public static LocalTime parseTime12Hours(String time) {
        time = time.toUpperCase();
        if (RegexUtils.validate(TIME_12HOURS_SHORT_PATTERN, time))
            time = "0" + time;
        return LocalTime.parse(time, TIME_12HOURS_FORMATTER);
    }

    public static LocalTime parseTime24Hours(String time) {
        if (RegexUtils.validate(TIME_24HOURS_SHORT_PATTERN, time))
            time = "0" + time;
        return LocalTime.parse(time, TIME_24HOURS_FORMATTER);
    }

    public static String formatTime12Hours(LocalTime time) {
        return time.format(TIME_12HOURS_FORMATTER);
    }

    public static String formatTime24Hours(LocalTime time) {
        return time.format(TIME_24HOURS_FORMATTER);
    }

    public static String formatZoneDateTime(ZonedDateTime time) {
        return time.format(ZONE_DATE_TIME_FORMATTER);
    }

    public static ZonedDateTime parseZoneDateTime(String time) {
        return ZonedDateTime.parse(time, ZONE_DATE_TIME_FORMATTER);
    }

    public static LocalDate parseLocalDate(String date) {
        return LocalDate.parse(date, LOCAL_DATE_FORMATTER);
    }

    public static LocalDate parseLocalDateYYYYMMDD(String date) {
        return LocalDate.parse(date, LOCAL_DATE_FORMATTER_YYYY_MM_DD);
    }

    public static ZonedDateTime convertZoneDateTime(LocalDate localDate, int hour, int minute, int second, ZoneId zoneId) {
        return localDate.atTime(hour, minute, second).atZone(zoneId == null ? ZoneId.systemDefault() : zoneId);
    }

    public static String formatLocalDate(LocalDate date) {
        return date.format(LOCAL_DATE_FORMATTER);
    }

    public static String formatDateNow() {
        return LocalDate.now().format(LOCAL_DATE_FORMATTER);
    }

    public static String formatDateYesterday() {
        LocalDate now = LocalDate.now();
        return now.plusDays(-1).format(LOCAL_DATE_FORMATTER);
    }

    public static String formatDateTomorrow() {
        LocalDate now = LocalDate.now();
        return now.plusDays(1).format(LOCAL_DATE_FORMATTER);
    }

    public static String formatYYYYMMDDNow() {
        return LocalDate.now().format(LOCAL_DATE_FORMATTER_YYYY_MM_DD);
    }

    public static String formatYYYYMMDDYesterday() {
        return LocalDate.now().plusDays(-1).format(LOCAL_DATE_FORMATTER_YYYY_MM_DD);
    }

    public static String formatYYYYMMDDTomorrow() {
        return LocalDate.now().plusDays(1).format(LOCAL_DATE_FORMATTER_YYYY_MM_DD);
    }

    public static String formatZoneDateTimeFull(ZonedDateTime time) {
        return time.format(FULL_TIME_ZONE_DATE_TIME_FORMATTER);
    }

    public static ZonedDateTime nowZoneHCM() {
        return ZonedDateTime.now(ZONE_HCM);
    }
}
