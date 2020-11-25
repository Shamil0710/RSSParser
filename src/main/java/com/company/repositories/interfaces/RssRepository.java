package com.company.repositories.interfaces;

import com.company.entities.abstraction.AbstractRSSElement;

import java.util.List;

public interface RssRepository<E extends AbstractRSSElement> {

    List<E> getAll();
    List<E> sort(String fieldName);
    List<E> filter(String request, String fieldName);

}
