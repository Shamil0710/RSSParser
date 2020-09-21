package com.company.parser;

import java.util.Comparator;

//Класс для сортировки по дате //todo проверить

public class RSSCompareDate implements Comparator<RSSElement> {
    @Override
    public  int compare(RSSElement o1, RSSElement o2) {
        return o1.getPublicationDate().compareTo(o2.getPublicationDate());
    }
}
