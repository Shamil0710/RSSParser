package com.company.collectors.abstraction;

import com.company.collectors.interfaces.IRSSCollector;
import com.company.entities.abstraction.AbstractRSSComponent;
import com.company.entities.abstraction.AbstractRSSElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public abstract class AbstractRSSCollector<E extends AbstractRSSElement, C extends AbstractRSSComponent> implements IRSSCollector<E, C> {

    protected LocalDateTime parseDate(String pubDate, String dataFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dataFormat, Locale.ROOT);
        return LocalDateTime.parse(pubDate, dateTimeFormatter);
    }

    @Override
    public abstract List<E> collectRSSElements(C component);
}

