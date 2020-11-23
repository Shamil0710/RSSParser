package com.company.interfaces;

import com.company.abstraction.AbstractRSSElement;
import com.company.parser.RSSElement;

import java.util.List;

public interface RssRepository<E extends AbstractRSSElement> {

    List<E> getAll();
    List<E> sort(String fieldName);
    List<E> filter(String request, String fieldName);

}
