package com.company.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RSSDateParser {
    public static LocalDateTime parseDate(String pubDate, String dataFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dataFormat, Locale.ROOT);
        LocalDateTime localDate = LocalDateTime.parse(pubDate, dateTimeFormatter);
        return localDate;
    }
}
