package com.company.interfaces;

import com.company.abstraction.AbstractRSSComponent;
import com.company.abstraction.AbstractRSSElement;

import java.util.List;


public interface IRSSCollector<E extends AbstractRSSElement, C extends AbstractRSSComponent> {
    List<E> collectRSSElements(C component);
}
