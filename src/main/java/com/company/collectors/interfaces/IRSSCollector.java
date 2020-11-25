package com.company.collectors.interfaces;

import com.company.entities.abstraction.AbstractRSSComponent;
import com.company.entities.abstraction.AbstractRSSElement;

import java.util.List;


public interface IRSSCollector<E extends AbstractRSSElement, C extends AbstractRSSComponent> {
    List<E> collectRSSElements(C component);
}
